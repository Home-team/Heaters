<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Upload form</title>
<body>
<h2>Fill in the form.</h2>
<form name="sendform" enctype="multipart/form-data" action="/loadImg" method="post">
<table>
 <tr>
 <td>File description:</td>
 <td>
  <input type="text" name="description ">
</td>
</tr>
<tr>
    <td>File to send:</td>
    <td>
        <input type="File" name="file_send">
    </td>
</tr>
</table>
<p>
    <input type="submit" value="Send">
</p>
</form>
</body>
</html>