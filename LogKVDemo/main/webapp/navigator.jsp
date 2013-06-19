<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>navigator</title>
	<meta http-equiv="Expires" content="0" />
	<meta http-equiv="Pragma" content="no-cache" /> 
	<meta http-equiv="Cache-Control" content="no-cache" /> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<style type="text/css">
	body {
		margin: 0;
		padding: 0;
		background: white;
		font-family: Arial, Helvetica, sans-serif;
		font-size: 10px;
		color: #787878;
		border: solid red;
		border-top-style: solid red;
		border-bottom-style: solid red;
		border-left-style: solid red;
		border-right-width : 2px;
	}
	
	.model_button {
		cursor : hand;
		border: solid white;
		border-width : 1px;
		color : #5151ff;
	}

	.model_button_over {
		cursor : hand;
		background-color : #797ce3;
		color : white;
	}
	</style>
	<script type="text/javascript">
		function changeBackGround(modelId) {
			
			for (var i = 1; i <= 5; i++) {
				document.getElementById("model" + i).className = 'model_button';
			}
			
			document.getElementById("model" + modelId).className = 'model_button_over';
		}
	</script>
</head>
<body>
<table width="100%" border="0">
	<tr>
		<td align="center" valign="top" height="60px">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td id="model1" align="center" height="60px" class="model_button" onclick="changeBackGround('1');top.welcome.location.href='./overview.jsp'">
			<font size="+1"><b>Overview</b></font>
		</td>
	</tr>
	<tr>
		<td align="center">
			<hr />
		</td>
	</tr>
	<tr>
		<td id="model2" align="center" height="60px" class="model_button" onclick="changeBackGround('2');top.welcome.location.href='./logsources.jsp'">
			<font size="+1"><b>Log Source</b></font>
		</td>
	</tr>
	<tr>
		<td align="center">
			<hr />
		</td>
	</tr>
	<tr>
		<td id="model3" align="center" height="60px" class="model_button" onclick="changeBackGround('3');top.welcome.location.href='./worknodes.jsp'">
			<font size="+1"><b>Worker Node</b></font>
		</td>
	</tr>
	<tr>
		<td align="center">
			<hr />
		</td>
	</tr>
	<tr>
		<td id="model4" align="center" height="60px" class="model_button" onclick="changeBackGround('4');top.welcome.location.href='./shufflingprocess.jsp'">
			<font size="+1"><b>Shuffling</b></font>
		</td>
	</tr>
	<tr>
		<td align="center">
			<hr />
		</td>
	</tr>
	<tr>
		<td id="model5" align="center" height="60px" class="model_button" onclick="changeBackGround('5');top.welcome.location.href='./query.jsp'">
			<font size="+1"><b>Query</b></font>
		</td>
	</tr>
</table>
</body>
</html>