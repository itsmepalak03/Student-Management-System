<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
<title>Login</title>

<style>

/* ===== PAGE ===== */

body{
    margin:0;
    font-family:'Segoe UI', Arial, sans-serif;
    background:#f1f5f9;

    display:flex;
    justify-content:center;
    align-items:center;
    height:100vh;
}

/* ===== LOGIN CARD ===== */

.login-container{

    width:380px;
    background:white;
    padding:40px;

    border-radius:14px;
    box-shadow:0 20px 40px rgba(0,0,0,0.06);
}

/* ===== TITLE ===== */

h2{
    text-align:center;
    margin-bottom:30px;
    color:#1f2937;
    font-weight:700;
}

/* ===== LABELS ===== */

label{
    display:block;
    font-size:14px;
    font-weight:600;
    color:#374151;
    margin-bottom:5px;
}

/* ===== INPUTS ===== */

input[type="text"],
input[type="password"]{

    width:100%;
    padding:12px;
    margin-bottom:18px;

    border:1px solid #d1d5db;
    border-radius:8px;

    font-size:14px;
}

input:focus{
    outline:none;
    border-color:#2563eb;
    box-shadow:0 0 0 2px rgba(37,99,235,0.2);
}

/* ===== BUTTON ===== */

input[type="submit"]{

    width:100%;
    padding:12px;

    background:#2563eb;
    color:white;

    border:none;
    border-radius:8px;

    font-weight:600;
    cursor:pointer;
}

input[type="submit"]:hover{
    background:#1d4ed8;
}

/* ===== ALERTS ===== */

.alert-success{
    background:#ecfdf5;
    color:#065f46;
    padding:12px;
    border-radius:8px;
    margin-bottom:15px;
    font-size:14px;
}

.message-error{
    background:#fef2f2;
    color:#991b1b;
    padding:12px;
    border-radius:8px;
    margin-bottom:15px;
    font-size:14px;
}

/* Hide empty messages automatically */

.alert-success:empty,
.message-error:empty{
    display:none;
}

</style>

</head>

<body>

<div class="login-container">

    <h2>Login</h2>

    <form action="LoginServlet" method="post">

        <!-- SUCCESS MESSAGE -->
        <div class="alert-success">
            ${logoutSuccess}
        </div>

        <!-- ERROR MESSAGE -->
        <div class="message-error">
            ${loginAccess}
        </div>

        <label>Username</label>
        <input type="text" name="username" placeholder="Enter username" required>

        <label>Password</label>
        <input type="password" name="password" placeholder="Enter password" required>

        <input type="submit" value="Login">

    </form>

</div>

</body>
</html>
