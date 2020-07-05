<!DOCTYPE text>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test jsp with servlet</title>
    </head>
    <body>
        <h1>Hello JSP PAGE</h1>
            <form>
               <p>Enter you name</p>
               <p><input name="name">
               <button type="submit" formmethod="GET" charset="UTF-8"><img src="images\1.png">Send</button></p>
               ${name}
            </form>
            <strong>
                <%
                            String data = (String)request.getAttribute("key");
                            out.write("<br/>first write version<br/>");
                            for (int i = 0; i < 3; i++) {
                                out.write(data+" "+i +"<br/>");
                            }
                            out.write("<br/>");
                %>
            </strong>

            <strong>
            <% out.write("<br/>second write version<br/>"); %>
                <%=request.getAttribute("key") %>
                <% out.write("<br/>"); %>
            </strong>




    </body>
</html>
