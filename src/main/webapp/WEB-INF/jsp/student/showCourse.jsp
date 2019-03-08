<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
	
	<div class="container" id="content">
	
	<div class="row">
	    <jsp:include page="menu.jsp"></jsp:include>
		<div class="col-md-10">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
					    	<h1 class="col-md-5">课程列表</h1>
							<form class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0;" action="/student/selectCourse" id="form1" method="post">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="请输入课程名" name="findByName">
									<span class="input-group-addon btn" onclick="document.getElementById('form1').submit" id="sub">搜索</span>
								</div>
							</form>
						</div>
				    </div>
				
				    	<table class="table table-bordered">
					        <thead>
					            <tr>
									<th>课程号</th>
									<th>课程名称</th>
									<th>授课老师编号</th>
									<th>上课时间</th>
									<th>上课地点</th>
									<th>周数</th>
									<th>课程类型</th>
									<th>学分</th>
									<th>操作</th>
					            </tr>
					        </thead>
					        <tbody>
							<c:forEach  items="${pagebean.list}" var="item">
								<tr>
									<td>${item.courseid}</td>
									<td>${item.coursename}</td>
									<td>${item.teacherid}</td>
									<td>${item.coursetime}</td>
									<td>${item.classroom}</td>
									<td>${item.courseweek}</td>
									<td>${item.coursetype}</td>
									<td>${item.score}</td>
									<td>
										<button class="btn btn-default btn-xs btn-info" onClick="location.href='/student/stuselectedCourse?id=${item.courseid}'">选课</button>
										<!--弹出框-->
									</td>
								</tr>
							</c:forEach>
					        </tbody>
				    </table>
				    
				    
                    <!-- 分页 totalCount在没有分页时，是没查数据库，没有赋值的-->
				    <div class="panel-footer">
						<c:if test="${pagebean.totalCount != null}">
							<nav style="text-align: center">
								<ul class="pagination">
									<li><a href="/student/showCourse?page=${pagebean.pagenum-1}">&laquo;上一页</a></li>
									
									<c:if test="${pagebean.pagenum-2 > 0}">
										<li><a href="/student/showCourse?page=${pagebean.pagenum-2}">${pagebean.pagenum-2}</a></li>
									</c:if>
									<c:if test="${pagebean.pagenum-1 > 0}">
										<li><a href="/student/showCourse?page=${pagebean.pagenum-1}">${pagebean.pagenum-1}</a></li>
									</c:if>
									
									<li class="active"><a href="">${pagebean.pagenum}</a></li>
									
									<c:if test="${pagebean.pagenum+1 <= (pagebean.totalCount+pagebean.pagesize-1)/pagebean.pagesize}">
										<li><a href="/student/showCourse?page=${pagebean.pagenum+1}">${pagebean.pagenum+1}</a></li>
									</c:if>
									<c:if test="${pagebean.pagenum+2 <= (pagebean.totalCount+pagebean.pagesize-1)/pagebean.pagesize}">
										<li><a href="/student/showCourse?page=${pagebean.pagenum+2}">${pagebean.pagenum+2}</a></li>
									</c:if>
									
									<li><a href="/student/showCourse?page=${pagebean.pagenum+1}">下一页&raquo;</a></li>
								</ul>
							</nav>
						</c:if>
				    </div>
			
<!-- 				    <script type="text/javascript"> -->
<!-- 							console.log("pagenum:" + ${pagebean.pagenum} + "  totalpage:"+${(pagebean.totalCount+pagebean.pagesize-1)/pagebean.pagesize}); -->
<!--                     </script> -->
				    
				  
	            </div>
	    </div>
	</div>
	
	</div>
	
	
     <!--底部 -->
	<div class="container" id="footer">
		<div class="row">
			<div class="col-md-12"></div>
		</div>
	</div>
</body>
</html>

<script type="text/javascript">
		<%--设置菜单中--%>
		$("#nav li:nth-child(1)").addClass("active")
//         <c:if test="${pagingVO != null}">
//         if (${pagebean.pagenum} == ${pagingVO.totalCount}) {
//             $(".pagination li:last-child").addClass("disabled")
//         };

//         if (${pagebean.pagenum} == ${1}) {
//             $(".pagination li:nth-child(1)").addClass("disabled")
//         };
//         </c:if>

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