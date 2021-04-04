/**
 * SNS 공유 javascript
 */
function fnShareSNS(type, title, url){
	let urlStr = '';
	if(url){
		urlStr = url;
	} else {
		urlStr = location.href;
	}
	
	let titleStr = '';
	if(title){
		titleStr = title;
	} else {
		titleStr = '컨텐츠관리시스템';	
	}
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
		break;
	case 'band':
		snsUrl = 'http://www.band.us/plugin/share?body='+encodeTitleStr+encodeURIComponent("\n")+encodeUrlStr+'&route='+encodeUrlStr;
		break;
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