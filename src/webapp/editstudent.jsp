<!DOCTYPE html>
<html>
<head>
	
    <title>Student Registration Form</title> 
<style>

body{
    background:#f4f6fb;
    font-family:'Segoe UI',Tahoma,sans-serif;
    margin:0;
}

/* ===== HEADER ===== */

.header{
    background:white;
    padding:15px 40px;
    display:flex;
    justify-content:space-between;
    align-items:center;
    box-shadow:0 2px 10px rgba(0,0,0,0.05);
}

.welcome-text{
    font-size:18px;
    font-weight:600;
    color:#2c3e50;
}

.logout-btn{
    background:#ff4d4f;
    color:white;
    padding:8px 16px;
    border-radius:6px;
    text-decoration:none;
    font-weight:600;
}

.logout-btn:hover{
    background:#d9363e;
}

/* ===== PAGE WRAPPER ===== */

.page-wrapper{
    max-width:700px;
    margin:40px auto;
    padding:0 20px;
}

/* ===== CARD ===== */

.container{
    background:white;
    padding:35px;
    border-radius:14px;
    box-shadow:0 8px 25px rgba(0,0,0,0.08);
}

/* ===== TITLE ===== */

h2{
    text-align:center;
    margin-bottom:30px;
    color:#2c3e50;
    font-weight:600;
}

/* ===== FORM ELEMENTS ===== */

label{
    font-size:14px;
    font-weight:600;
    color:#444;
}

input,select,textarea{
    width:100%;
    padding:12px;
    margin-top:6px;
    margin-bottom:18px;
    border:1px solid #ddd;
    border-radius:6px;
    transition:0.3s;
    font-size:14px;
}

input:focus,select:focus,textarea:focus{
    outline:none;
    border-color:#1677ff;
    box-shadow:0 0 5px rgba(22,119,255,0.3);
}

/* readonly styling */

input[readonly]{
    background:#f3f4f6;
    cursor:not-allowed;
}

/* radio checkbox alignment */

.option-group{
    margin-bottom:18px;
}

.option-group label{
    font-weight:normal;
    margin-right:15px;
}

input[type="radio"],
input[type="checkbox"]{
    width:auto;
    margin-right:5px;
}

/* ===== BUTTONS ===== */

.btn-container{
    display:flex;
    justify-content:center;
    gap:15px;
    margin-top:20px;
}

input[type="submit"]{
    background:#1677ff;
    color:white;
    border:none;
    padding:12px 25px;
    border-radius:6px;
    font-weight:600;
    cursor:pointer;
}

input[type="submit"]:hover{
    background:#0958d9;
}

input[type="reset"]{
    background:#6c757d;
    color:white;
    border:none;
    padding:12px 25px;
    border-radius:6px;
    font-weight:600;
    cursor:pointer;
}

input[type="reset"]:hover{
    background:#545b62;
}

</style>
    
</head>

<body>
<%@page import="com.royal.bean.UserBean"%>

<%
UserBean userBean = (UserBean)session.getAttribute("userBean");

if(userBean == null){
    request.setAttribute("loginAccess","<font color='red'>Please login first.</font>");
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



<div class="page-wrapper">

<div class="container">
    <h2>Edit Student Registration Form</h2>

    <form action="UpdateStudentServlet" method="post">

        <label>Id:</label>  ${idErr}
        <input type="text" name="id" value="${sbean.id}" readonly="readonly"> 
    
    
        <label>Full Name:</label>  ${fullnameErr}
        <input type="text" name="fullname" value="${sbean.fullname}"> 

        <label>Age:</label>  ${ageErr}
        <input type="number" name="age" value="${sbean.age}">

        <label>Select Course:</label> ${courseErr}
        <select name="course">   
            <option value="">-- Select Course --</option>

            <option value="C Programming" ${sbean.course == 'C Programming' ? 'selected' : ''} >C Programming</option>
            
            <option value="C++"  ${sbean.course == 'C++' ? 'selected' : ''}>C++</option>
            
            <option value="Core Java"   ${sbean.course == 'Core Java' ? 'selected' : ''} >Core Java</option>
            
            <option value="Advanced Java"   ${sbean.course == 'Advanced Java' ? 'selected' : ''}>Advanced Java</option>
            
            <option value="Python"   ${sbean.course == 'Python' ? 'selected' : ''}>Python</option>
            
        </select>

        <label>Gender:</label>
		<div class="option-group">
		    <label><input type="radio" name="gender" value="Male" ${sbean.gender == 'Male' ? 'checked' : ''}> Male</label>
		    <label><input type="radio" name="gender" value="Female" ${sbean.gender == 'Female' ? 'checked' : ''}> Female</label>
		    <label><input type="radio" name="gender" value="Other" ${sbean.gender == 'Other' ? 'checked' : ''}> Other</label>
		</div>



       <label>Hobbies:</label>
		<div class="option-group">
		    <label><input type="checkbox" name="hobby" value="Reading" ${sbean.isAvailableHobby('Reading') ? 'checked' : ''}> Reading</label>
		    <label><input type="checkbox" name="hobby" value="Music" ${sbean.isAvailableHobby('Music') ? 'checked' : ''}> Music</label>
		    <label><input type="checkbox" name="hobby" value="Sports" ${sbean.isAvailableHobby('Sports') ? 'checked' : ''}> Sports</label>
		    <label><input type="checkbox" name="hobby" value="Coding" ${sbean.isAvailableHobby('Coding') ? 'checked' : ''}> Coding</label>
		</div>


        <label>Date of Birth:</label>${dobErr}
        <input type="date" name="dob" value="${sbean.dob}"> 

        <label>Email ID:</label>  ${emailErr}
        <input type="email" name="email" value="${sbean.email}" readonly="readonly">

        <label>Mobile Number:</label>${mobileErr}
        <input type="tel" name="mobile" value="${sbean.mobile}">

        <label>Address:</label>${addressErr}
        <textarea name="address" rows="3">${sbean.address}</textarea>

        <div class="btn-container">
            <input type="submit" value="Update">
            <input type="reset" value="Clear">
        </div>
    </form>
</div>
</div>
</body>
</html>
