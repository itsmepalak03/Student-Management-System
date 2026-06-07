<%@page import="java.util.ArrayList"%>
<%@page import="com.royal.bean.StudentBean"%>
<%@page import="com.royal.bean.UserBean"%>

<%
    UserBean userBean = (UserBean) session.getAttribute("userBean");

    if(userBean == null){
        response.sendRedirect("Login.jsp");
        return;
    }

    ArrayList<StudentBean> list =
        (ArrayList<StudentBean>) request.getAttribute("list");

    int currentPage = (int) request.getAttribute("currentPage");
    int totalPages  = (int) request.getAttribute("totalPages");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Management</title>

<style>

body{
    margin:0;
    font-family: 'Segoe UI', sans-serif;
    background: linear-gradient(135deg,#e3f2fd,#f1f8e9);
}

/* Header */
.header{
    background:#1e88e5;
    padding:20px;
    color:white;
    display:flex;
    justify-content:space-between;
    align-items:center;
}

.header h2{
    margin:0;
    font-weight:500;
}

.logout-btn{
    background:white;
    color:#1e88e5;
    padding:8px 15px;
    border-radius:20px;
    text-decoration:none;
    font-weight:500;
    transition:0.3s;
}

.logout-btn:hover{
    background:#1565c0;
    color:white;
}

/* Card */
.card{
    width:95%;
    margin:30px auto;
    background:white;
    padding:20px;
    border-radius:10px;
    box-shadow:0 5px 15px rgba(0,0,0,0.1);
}

/* Table */
table{
    width:100%;
    border-collapse:collapse;
}

th{
    background:#43a047;
    color:white;
    padding:12px;
}

td{
    padding:10px;
    text-align:center;
}

tr:nth-child(even){
    background:#f5f5f5;
}

tr:hover{
    background:#e8f5e9;
}

/* Action Buttons */
.action-btn{
    padding:5px 10px;
    border-radius:5px;
    text-decoration:none;
    color:white;
    font-size:13px;
}

.edit{
    background:#0288d1;
}

.delete{
    background:#e53935;
}

.action-btn:hover{
    opacity:0.8;
}

/* Pagination */
.pagination{
    margin-top:20px;
    text-align:center;
}

.pagination a{
    padding:8px 12px;
    margin:3px;
    border-radius:5px;
    background:#43a047;
    color:white;
    text-decoration:none;
    transition:0.3s;
}

.pagination a:hover{
    background:#2e7d32;
}

.activePage{
    background:#ff9800 !important;
}

</style>
</head>

<body>

<div class="header">
    <h2>Welcome, <%= userBean.getName() %></h2>
    <a class="logout-btn" href="LogoutServlet">Logout</a>
</div>

<div class="card">

<table>
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Age</th>
    <th>Course</th>
    <th>Gender</th>
    <th>Hobbies</th>
    <th>DOB</th>
    <th>Email</th>
    <th>Mobile</th>
    <th>Address</th>
    <th>Action</th>
</tr>

<%
if(list != null && list.size() > 0){
    for(StudentBean s : list){
%>

<tr>
    <td><%=s.getId()%></td>
    <td><%=s.getFullname()%></td>
    <td><%=s.getAge()%></td>
    <td><%=s.getCourse()%></td>
    <td><%=s.getGender()%></td>

    <td>
        <%
            String hobbies[] = s.getHobby();
            if(hobbies != null){
                for(String h : hobbies){
                    out.print(h + " ");
                }
            }
        %>
    </td>

    <td><%=s.getDob()%></td>
    <td><%=s.getEmail()%></td>
    <td><%=s.getMobile()%></td>
    <td><%=s.getAddress()%></td>

    <td>
        <a class="action-btn edit"
           href="EditStudentServlet?id=<%=s.getId()%>">Edit</a>

        <a class="action-btn delete"
           href="DeleteStudentServlet?id=<%=s.getId()%>"
           onclick="return confirm('Are you sure?')">
           Delete
        </a>
    </td>
</tr>

<%
    }
}else{
%>
<tr>
    <td colspan="11">No Records Found</td>
</tr>
<%
}
%>

</table>

<!-- Pagination -->

<div class="pagination">

<%
if(currentPage > 1){
%>
<a href="ListStudentServlet?page=<%=currentPage-1%>">Previous</a>
<%
}

for(int i=1;i<=totalPages;i++){
    if(i==currentPage){
%>
<a class="activePage"
   href="ListStudentServlet?page=<%=i%>"><%=i%></a>
<%
    }else{
%>
<a href="ListStudentServlet?page=<%=i%>"><%=i%></a>
<%
    }
}

if(currentPage < totalPages){
%>
<a href="ListStudentServlet?page=<%=currentPage+1%>">Next</a>
<%
}
%>

</div>

</div>

</body>
</html>
