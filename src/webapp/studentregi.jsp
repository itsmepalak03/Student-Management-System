<!DOCTYPE html>
<%@page import="com.royal.bean.UserBean"%>
<html>
<head>

<title>Student Registration Form</title>

<style>

body {
    background: #eef2f7;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    margin:0;
}

/* ===== HEADER ===== */

.header {
    width:100%;
    background:white;
    padding:15px 30px;
    box-shadow:0px 2px 8px rgba(0,0,0,0.08);
    display:flex;
    justify-content:space-between;
    align-items:center;
    box-sizing:border-box;
}

.welcome-text{
    font-size:18px;
    font-weight:600;
    color:#333;
}

.logout-btn {
    background:#ff4d4f;
    color:white;
    padding:8px 14px;
    text-decoration:none;
    border-radius:6px;
    font-weight:bold;
    transition:0.3s;
}

.logout-btn:hover {
    background:#d9363e;
}

/* ===== FORM CARD ===== */

.container {
    width:500px;
    background:white;
    margin:40px auto;
    padding:30px;
    box-shadow:0px 6px 18px rgba(0,0,0,0.1);
    border-radius:10px;
}

h2 {
    text-align:center;
    color:#2c3e50;
    margin-bottom:25px;
}

/* ===== FORM ELEMENTS ===== */

label {
    font-size:14px;
    color:#444;
    font-weight:600;
}

input, select, textarea {
    width:100%;
    padding:10px;
    margin:6px 0 18px 0;
    border:1px solid #ccc;
    border-radius:6px;
    box-sizing:border-box;
    transition:0.3s;
}

input:focus, select:focus, textarea:focus {
    outline:none;
    border-color:#007bff;
    box-shadow:0 0 4px rgba(0,123,255,0.3);
}

input[type="radio"], input[type="checkbox"]{
    width:auto;
    margin-right:5px;
}

/* ===== BUTTONS ===== */

.btn-container {
    text-align:center;
}

input[type="submit"], input[type="reset"] {
    width:130px;
    padding:12px;
    border:none;
    color:white;
    font-weight:bold;
    border-radius:6px;
    cursor:pointer;
    transition:0.3s;
}

input[type="submit"] {
    background:#007bff;
}

input[type="submit"]:hover {
    background:#0056b3;
}

input[type="reset"] {
    background:#6c757d;
}

input[type="reset"]:hover {
    background:#545b62;
}

</style>
</head>

<body>

<%
    UserBean userBean = (UserBean)session.getAttribute("userBean");

    if(userBean == null)
    {
        request.setAttribute("loginAccess", "<font color='red'>Please login first.</font>");
        request.getRequestDispatcher("login.jsp").forward(request,response);
        return;
    }
%>

<div class="header">

    <div class="welcome-text">
        Welcome, ${userBean.name}
    </div>

    <a href="LogoutServlet" class="logout-btn">Logout</a>

</div>

<div class="container">

<h2>Student Registration Form</h2>

<form action="InsertStudentServlet" method="post">

    <label>Full Name:</label> ${fullnameErr}
    <input type="text" name="fullname" value="${sbean.fullname}">

    <label>Age:</label> ${ageErr}
    <input type="number" name="age" value="${sbean.age}">

    <label>Select Course:</label> ${courseErr}
    <select name="course">
        <option value="">-- Select Course --</option>

        <option value="C Programming" ${sbean.course == 'C Programming' ? 'selected' : ''}>C Programming</option>
        <option value="C++" ${sbean.course == 'C++' ? 'selected' : ''}>C++</option>
        <option value="Core Java" ${sbean.course == 'Core Java' ? 'selected' : ''}>Core Java</option>
        <option value="Advanced Java" ${sbean.course == 'Advanced Java' ? 'selected' : ''}>Advanced Java</option>
        <option value="Python" ${sbean.course == 'Python' ? 'selected' : ''}>Python</option>
    </select>

    <label>Gender:</label> ${genderErr}<br>
    <input type="radio" name="gender" value="Male" ${sbean.gender == 'Male' ? 'checked' : ''}> Male
    <input type="radio" name="gender" value="Female" ${sbean.gender == 'Female' ? 'checked' : ''}> Female
    <input type="radio" name="gender" value="Other" ${sbean.gender == 'Other' ? 'checked' : ''}> Other
    <br><br>

    <label>Hobbies:</label> ${hobbyErr}<br>
    <input type="checkbox" name="hobby" value="Reading" ${sbean.isAvailableHobby('Reading') ? 'checked' : ''}> Reading
    <input type="checkbox" name="hobby" value="Music" ${sbean.isAvailableHobby('Music') ? 'checked' : ''}> Music
    <input type="checkbox" name="hobby" value="Sports" ${sbean.isAvailableHobby('Sports') ? 'checked' : ''}> Sports
    <input type="checkbox" name="hobby" value="Coding" ${sbean.isAvailableHobby('Coding') ? 'checked' : ''}> Coding
    <br><br>

    <label>Date of Birth:</label> ${dobErr}
    <input type="date" name="dob" value="${sbean.dob}">

    <label>Email ID:</label> ${emailErr}
    <input type="email" name="email" value="${sbean.email}">

    <label>Mobile Number:</label> ${mobileErr}
    <input type="tel" name="mobile" value="${sbean.mobile}">

    <label>Address:</label> ${addressErr}
    <textarea name="address" rows="3">${sbean.address}</textarea>

    <div class="btn-container">
        <input type="submit" value="Register">
        <input type="reset" value="Clear">
    </div>

</form>

</div>

</body>
</html>
