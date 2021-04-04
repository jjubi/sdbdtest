/**
 * 설문 시나리오 HTML 관리 javascript
 */
/*
 * 시나리오 기본 모달
 */
const defaultQestnarSenarioModalHTML = '<div class="modal fade" id="senarioModal" aria-labelledby="senarioModalLabel" aria-hidden="true" data-backdrop="static">'
										+'	<div class="modal-dialog modal-lg">'
										+'		<div class="modal-content">'
										+'			<div class="modal-header">'
										+'				<h4 class="modal-title" id="senarioModalLabel">설문조사 시나리오 관리</h4>'
										+'			</div>'
										+'			<div class="modal-body form-horizontal">'
										+'				<form id="qestnarSenarioForm">'
										+'					<input type="hidden" id="nowQestnarSeqNo" name="nowQestnarSeqNo" value="">'
										+'					<div class="col">'
										+'						<div class="row">'
										+'							<h5 class="col">시나리오 목록</h5>'
										+'							<div class="col-auto">'
										+'								<a href="javascript:;" class="btn btn-sm btn-primary addSenarioBtn">'
										+'									<i class="far fa-plus-square"></i> 추가'
										+'								</a>'
										+'							</div>'
										+'						</div>'
										+'					</div>'
										+'					<div class="senario-group">'
										+'					</div>'
										+'				</form>'
										+'			</div>'
										+'			<div class="modal-footer">'
										+'				<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>'
										+'				<button type="button" id="saveBtn" class="btn btn-primary">저장</button>'
										+'			</div>'
										+'		</div>'
										+'	</div>'
										+'</div>';

const defaultQestnHTML = '<div class="col senario">'
						+'	<div class="row mb-2 dtrmnQestn">'
						+'		<div class="row col-md-12 pr-0">'
						+'			<div class="col-md-11">'
						+'				<select class="form-control" name="dtrmnQestnSeqNo">'
						+'				</select>'
						+'			</div>'
						+'			<a class="btn btn-sm btn-danger deleteBtn col-md-1 p-0 pt-2"><i class="far fa-minus-square"></i> 삭제</a>'
						+'		</div>'
						+'	</div>'
						+'</div>';

const defaultLogicHTML = '<div class="dtrmnLogicCnd col-md-2">'
						+'	<select class="form-control" name="dtrmnLogicCnd">'
						+'	</select>'
						+'</div>';

const defaultCndHTML = '<div class="dtrmnCnd col-md-2">'
					+'	<select class="form-control" name="dtrmnCnd">'
					+'	</select>'
					+'</div>';

const defaultValueTextHTML = '<div class="dtrmnValue col-md-7">'
							+'	<input type="text" class="form-control" name="dtrmnValue" value="" placeholder="">'
							+'</div>';

const defaultValueSelectHTML = '<div class="dtrmnValue col-md-7">'
							+'	<select class="form-control" name="dtrmnValue">'
							+'	</select>'
							+'</div>';

const cndAddBtnHTML = '<a class="btn btn-sm btn-info pt-2 addOption"><i class="far fa-plus-square"></i></a>';
const cndMinusBtnHTML = '<a class="btn btn-sm btn-danger pt-2 deleteOption"><i class="far fa-minus-square"></i></a>';

const defaultSenarioCndHTML = '<div class="row mb-2 cndValueOption">'
								+'	<div class="row col-md-12 pr-0 cndValues">'
								+'	</div>'
								+'</div>';

const defaultTrgetQestnHTML = '<div class="row mb-2 trgetQestn">'
							+'	<div class="row col-md-12 pr-0">'
							+'		<div class="col-md-11">'
							+'			<select class="form-control" name="trgetQestnSeqNo">'
							+'			</select>'
							+'		</div>'
							+'	</div>'
							+'</div>';









