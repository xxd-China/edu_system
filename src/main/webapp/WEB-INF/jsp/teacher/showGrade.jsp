<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<title>课程信息显示</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 引入bootstrap -->
	<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
	<!-- 引入JQuery  bootstrap.js-->
	<script src="/js/jquery-3.2.1.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>

	<%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>

</head>
<body>
	<!-- 顶栏 -->
	<jsp:include page="top.jsp"></jsp:include>
	<!-- 中间主体 -->
	<div class="container" id="content">
		<div class="row">
			<jsp:include page="menu.jsp"></jsp:include>
			<div class="col-md-10">
				<div class="panel panel-default">
				    <div class="panel-heading">
						<div class="row">
					    	<h1 class="col-md-5">已选该课程学生名单</h1>
						</div>
				    </div>
				    <table class="table table-bordered">
					        <thead>
					            <tr>
									<th>学号</th>
									<th>姓名</th>
									<th>分数</th>
									<th>操作</th>
					            </tr>
					        </thead>

					        <tbody>
								<c:forEach items="${pagebean.list}" var="item">
									<tr>
				                		<td>${item.studentid}</td>
										<td>${item.userName}</td>
										<c:if test="${!item.over}">
											<td>未打分</td>
											<td>
												<button class="btn btn-default btn-xs btn-info" onClick="location.href='/teacher/mark?studentid=${item.studentid}&courseid=${item.courseid}'">打分</button>
											</td>
										</c:if>
										
											<td>${item.mark}</td>
										<c:if test="${item.over}">
											<td>已打分</td>
										</c:if>
									</tr>
								</c:forEach>
					        </tbody>
				    </table>
				 <!-- 分页 totalCount在没有分页时，是没查数据库，没有赋值的-->
				    <div class="panel-footer">
						<c:if test="${pagebean.totalCount != null}">
							<nav style="text-align: center">
								<ul class="pagination">
									<li><a href="/teacher/gradeCourse?page=${pagebean.pagenum-1}">&laquo;上一页</a></li>
									
									<c:if test="${pagebean.pagenum-2 > 0}">
										<li><a href="/teacher/gradeCourse?page=${pagebean.pagenum-2}">${pagebean.pagenum-2}</a></li>
									</c:if>
									<c:if test="${pagebean.pagenum-1 > 0}">
										<li><a href="/teacher/gradeCourse?page=${pagebean.pagenum-1}">${pagebean.pagenum-1}</a></li>
									</c:if>
									
									<li class="active"><a href="">${pagebean.pagenum}</a></li>
									
									<c:if test="${pagebean.pagenum+1 <= (pagebean.totalCount+pagebean.pagesize-1)/pagebean.pagesize}">
										<li><a href="/teacher/gradeCourse?page=${pagebean.pagenum+1}">${pagebean.pagenum+1}</a></li>
									</c:if>
									<c:if test="${pagebean.pagenum+2 <= (pagebean.totalCount+pagebean.pagesize-1)/pagebean.pagesize}">
										<li><a href="/teacher/gradeCourse?page=${pagebean.pagenum+2}">${pagebean.pagenum+2}</a></li>
									</c:if>
									
									<li><a href="/teacher/gradeCourse?page=${pagebean.pagenum+1}">下一页&raquo;</a></li>
								</ul>
							</nav>
						</c:if>
				    </div>
				    
				    
				</div>

			</div>
		</div>
	</div>
	<div class="container" id="footer">
		<div class="row">
			<div class="col-md-12"></div>
		</div>
	</div>
</body>
	<script type="text/javascript">
	

        function confirmd() {
            var msg = "您真的确定要删除吗？！";
            if (confirm(msg)==true){
                return true;
            }else{
                return false;
            }
        }

        $("#sub").click(function () {
            $("#form1").submit();
        });
	</script>
</html>