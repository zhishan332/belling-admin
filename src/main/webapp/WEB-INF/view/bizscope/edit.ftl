<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>表单</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">
		<#include "/widget/common-css.html">
	</head>

	<body>
		<div style="margin: 15px;">
			<fieldset class="layui-elem-field layui-field-title none" style="margin-top: 20px;">
				<legend>填写用户信息</legend>
			</fieldset>
			<form class="layui-form layui-form-pane" action="${base}/biz/scope/save" method="post">
				<div class="layui-form-item">
					<div class="layui-input-inline">
						<input type="hidden" name="id" value="${vo.id}" lay-verify="id" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">区域名称</label>
					<div class="layui-input-inline">
						<input type="text" name="bizName" value="${vo.bizName}"  lay-verify="required"   placeholder="必填项" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">区域编码</label>
					<div class="layui-input-inline">
						<input type="text" name="bizValue"  value="${vo.bizValue}" lay-verify="required"  placeholder="必填项" autocomplete="off" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux">必须唯一</div>
				</div>
                <div class="layui-form-item">
                    <label class="layui-form-label">地址</label>
                    <div class="layui-input-inline">
                        <input type="text" name="bizAddr" value="${vo.bizAddr}"  placeholder="请输入地址" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">联系方式</label>
                    <div class="layui-input-inline">
                        <input type="text" name="bizContact" value="${vo.bizContact}"  placeholder="请输入联系方式" autocomplete="off" class="layui-input">
                    </div>
                </div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="btnSub">
							<i class="layui-icon">&#xe605;</i> 提 交
						</button>
						<button type="reset" class="layui-btn layui-btn-normal">
							<i class="layui-icon">&#xe60e;</i> 重置
						</button> 
						<button type="button" class="layui-btn layui-btn-primary" id="btnBack" lay-filter="btnBack">
							<i class="layui-icon">&#x1006;</i> 取 消
						</button>
					</div>
				</div>
			</form>
		</div>
		<script type="text/javascript" src="${base}/static/plugins/layui/layui.js"></script>
		<script type="text/javascript" src="${base}/static/js/app/bizscope/edit.js"></script>
	</body>
</html>