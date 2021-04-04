<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>

<c:set var="nowUrl"><c:out value="${requestScope['javax.servlet.forward.request_uri']}"/></c:set>

<!-- facebook 공유 -->
<script async defer crossorigin="anonymous" src="https://connect.facebook.net/ko_KR/sdk.js#xfbml=1&version=v9.0" nonce="8obwvogD"></script>
<!-- twitter 공유 -->
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>
<!-- band -->
<script type="text/javascript" src="//developers.band.us/js/share/band-button.js?v=30122020"></script>
<!-- line -->
<script src="https://www.line-website.com/social-plugins/js/thirdparty/loader.min.js" async="async" defer="defer"></script>
<!-- kakao -->
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>

<script>
var shareUrlStr = '';
$(document).ready(function(){
	// SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해 주세요.
	Kakao.init('${kakaoAPIKey}');

	if(!Kakao.isInitialized()){
		Swal.fire('확인', '카카오 SDK초기화를 실패하였습니다. 확인해주세요.', 'warning');	
	}
	
	if(isMobile){
		$('.mobileBtn').show();
		$('.pcBtn').hide();
	} else {
		$('.mobileBtn').hide();
		$('.pcBtn').show();
	}
	
	var shareNttId = '${shareNttId}';
	shareUrlStr = location.href;
	//게시물일 경우
	if(shareUrlStr.indexOf('nttView.do') > -1){
		shareUrlStr += "?nttId=" + shareNttId;
	}
	
	createClipboardCopyBtn($('.urlSNS-icon')[0], shareUrlStr);
	
// 	Kakao.Story.createShareButton({
// 		container: '#kakaoStorySNS-Button',
// 		url: location.href,
// 		text: '컨텐츠관리시스템'
// 	});
	
// 	Kakao.Link.createScrapButton({
// 		container: '#kakaoSNS-Button',
// 		requestUrl: 'https://developers.kakao.com'
// 	});
	
});
function fnShareSNS(type, title){
	let urlStr = shareUrlStr;
	let titleStr = (title != undefined ? title : '컨텐츠관리시스템');
	let encodeUrlStr = encodeURIComponent(urlStr);
	let encodeTitleStr = encodeURIComponent(titleStr);
	let snsUrl = '';
	let cw=600;
	let ch=500;
	let returnBool = false;
	
	switch(type){
	case 'facebook':
		snsUrl = "https://www.facebook.com/sharer/sharer.php?u="+encodeUrlStr;
		cw=1000; 
		ch=800;
		break;
	case 'twitter':
		snsUrl = 'https://twitter.com/intent/tweet?text='+encodeTitleStr+'&url='+encodeUrlStr;
		cw=720; 
		ch=350;
		break
	case 'band':
		return;
	case 'line':
		snsUrl = 'https://social-plugins.line.me/lineit/share?url='+encodeUrlStr;
		cw=800; 
		ch=700;
		break;
	case 'naverBlog':
		snsUrl = "https://share.naver.com/web/shareView.nhn?url=" + encodeUrlStr + "&title=" + encodeTitleStr;
		cw=800; 
		ch=700;
		break;
	case 'kakaoStory':
		if(!isMobile){
			Kakao.Story.share({
				url: urlStr,
				text: titleStr
			});
		} else {
			Kakao.Story.open({
				url: urlStr,
				text: titleStr
			});
		}
		break;
	case 'kakaoTalk':
		//스크랩템플릿
// 		Kakao.Link.sendScrap({
// 			requestUrl: 'https://developers.kakao.com',
// 		})
		Kakao.Link.sendDefault({
			objectType: 'feed',
			content: {
				title: titleStr,
				imageUrl: location.protocol + "//" + location.host + ":8080/static/images/vaiv/cmmn/adm/login/happiness.svg",
				link: {
					mobileWebUrl: urlStr,
					webUrl: urlStr
				}
			}
		});
		break;
	case 'url':
		return;
	default:
		return;
	}
	var parentWindow;
	//스크린의 크기
	let sw=screen.availWidth;
	let sh=screen.availHeight;
	
	//열 창의 포지션
	let px=(sw-cw)/2;
	let py=(sh-ch)/2;
	
	parentWindow = window.open(snsUrl, 'title', 'left='+px+', top='+py+', width='+cw+', height='+ch+', toolbar=no,menubar=no,status=no,scrollbars=no,resizable=no');
}


</script>


<div class="socialShareDiv">
	<div class="row">
		<div class="col"><h5>SNS 공유하기</h5></div>
		<div class="col-auto">
			<!-- facebook -->
			<i class="fab fa-facebook-square facebookSNS-icon" onclick="fnShareSNS('facebook');"></i>
		</div>
		<div class="col-auto">
			<!-- twitter -->
			<i class="fab fa-twitter-square twitterSNS-icon" onclick="fnShareSNS('twitter');"></i>
		</div>
		<div class="col-auto">
			<!-- band -->
			<span>
				<script type="text/javascript">
				    new ShareBand.makeButton({"lang":"ko-KR","type":"c"});
				</script>
			</span>
		</div>
		<div class="col-auto">
			<i class="fab fa-line lineSNS-icon" onclick="fnShareSNS('line');"></i>
		</div>
		<div class="col-auto">
			<img class="naverBlogSNS-icon" src="https://ssl.pstatic.net/share/images/appicon/naver_square_30x30.png" alt="네이버 공유" width="30" height="30" onclick="fnShareSNS('naverBlog');">
		</div>
		<div class="col-auto">
			<img class="kakaoStorySNS-icon" src="https://developers.kakao.com/sdk/js/resources/story/icon_small.png" width="30" height="30" onclick="fnShareSNS('kakaoStory');"/>
		</div>
		<div class="col-auto">
			<img class="kakaoTalkSNS-icon" src="https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png" width="30" height="30" onclick="fnShareSNS('kakaoTalk');"/>
		</div>
		<div class="col-auto">
			<i class="fas fa-link urlSNS-icon" onclick="fnShareSNS('url');"></i>
		</div>
	</div>
</div>


