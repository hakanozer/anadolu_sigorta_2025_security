<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" crossorigin="anonymous"></script>
    <title>Login</title>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-sm-4"></div>
            <div class="col-sm-4">
                <h2>User Login</h2>
                <c:if test="${errors != null}">
                    <c:forEach items="${errors}" var="item">
                        <div class="alert alert-warning alert-dismissible fade show" role="alert">
                            <strong>${item.getField()}</strong> ${item.getDefaultMessage()}
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:forEach>
                </c:if>
                <c:out value="${data}"></c:out>
                <form method="post">
                    <div class="mb-3">
                        <input name="data" placeholder="Data.." class="form-control" />
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input required name="email" id="email" type="email" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input required minlength="5" maxlength="15" name="password" type="password" class="form-control" id="password">
                    </div>
                    <div class="mb-3">
                        <select name="city" class="form-select" aria-label="Default select example">
                            <option value="" >Select City</option>
                            <option value="istanbul">Istanbul</option>
                            <option value="ankara">Ankara</option>
                            <option value="izmir">Izmir</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">login</button>
                </form>
            </div>
            <div class="col-sm-4"></div>
        </div>
    </div>
</body>
</html>