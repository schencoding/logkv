<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.hp.hpl.logkv.demo.ContextParameter" %>
<html>
<head>
	<title>overview</title>
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
	}
	
	</style>
</head>
<body>
<table width="100%" height="100%">
	<tr>
		<td height="100%" width="100%" align="center" valign="middle">
			<APPLET  CODE="com.hp.hpl.logkv.demo.applet.workernodes.WorkerNodesApplet.class" 
				codebase="http://<%= ContextParameter.SERVER_IP %>:<%= ContextParameter.SERVER_PORT %>/LogKVDemo/LogKVDemo-client" WIDTH="99%" HEIGHT="100%" ALIGN="middle">
				 <PARAM NAME="serverIP" VALUE="<%= ContextParameter.SERVER_IP %>">
				 <PARAM NAME="serverPort" VALUE="<%= ContextParameter.SERVER_PORT %>">
			</APPLET>
		</td>
	</tr>
</table>
</body>
</html>