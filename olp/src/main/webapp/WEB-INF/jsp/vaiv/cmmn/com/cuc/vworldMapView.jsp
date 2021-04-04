<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<div id="vmap${not empty listNum ? listNum : '' }" style="width:100%;height:370px;left:0px;top:0px"></div>
 <div id="buttons">
  <button type="button" onclick="javascript:move(14129709.590359,4512313.7639686,15, '${not empty listNum ? listNum : 0}');" >여의도</button>
  <button type="button" onclick="javascript:move(14679304.585522275, 4472545.1240446,14, '${not empty listNum ? listNum : 0}');" >독도</button>
 </div>
<script type="text/javascript">
	vw.ol3.CameraPosition.center = ['${lng}', '${lat}'];
	vw.ol3.CameraPosition.zoom = 16;
	vw.ol3.MapOptions = {
		  basemapType: vw.ol3.BasemapType.GRAPHIC
		, controlDensity: vw.ol3.DensityType.EMPTY
		, interactionDensity: vw.ol3.DensityType.BASIC
		, controlsAutoArrange: true
		, homePosition: vw.ol3.CameraPosition
		, initPosition: vw.ol3.CameraPosition
	};

	vmap["${not empty listNum ? listNum : 0}"] = new vw.ol3.Map("vmap${not empty listNum ? listNum : '' }",  vw.ol3.MapOptions);
//     move('${lat}', '${lng}', '${not empty listNum ? listNum : 0 }', '${not empty listNum ? listNum : 0}');
	setTimeout("move('${lat}', '${lng}', '${not empty listNum ? listNum : 0 }', '${not empty listNum ? listNum : 0}')", 2000);
</script>