<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/taglib.jsp"%>
<form class="container mt-4" id='form-data'> 
	<div class="row">
		<h3 class="btn-link">Thùng rác</h3>
	</div>
	<hr>
	<div>
		<div class="row">
			<div class="col-6 d-flex flex-row">
				<a class="p-2 btn-link" href="/admin/product/display">Quay lại danh sách</a>
			</div>
			<div class="col-6 d-flex flex-row-reverse">
				<button class="p-2 ml-4 btn btn-outline-success btn-sm btn-restore-all" hidden>Khôi phục</button>
				<button class="p-2 ml-4 btn btn-outline-danger btn-sm btn-delete-all" hidden>Xóa</button>
			</div>
		</div>
		
		<table class="table mt-4">
		  <thead>
		    <tr>
		      <th scope="col"><input type="checkbox" id="checkbox-all"></th>
		      <th scope="col">#</th>
		      <th scope="col">ID</th>
		      <th scope="col">Ảnh</th>
		      <th scope="col">Tên</th>
		      <th scope="col">Thời gian xóa</th>
		      <th scope="col">ID danh mục</th>
		      <th scope="col" class="text-center">Công cụ</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<c:if test="${empty productsTrash}">
		  		<h3>Thùng rác rỗng. <a class="btn-link" href="/admin/product/display">Quay lại danh sách</a></h3>
		  	</c:if>
		    <c:forEach varStatus="loop" var="product" items="${productsTrash}">
		    	<tr id="row-table">
		    	  <th><input type="checkbox" id="checkbox-element" value="${product.id}"></th>
			      <th scope="row">${loop.count}</th>
			      <td>${product.id}</td>
			      <td><img style="width: 100px;" src="${product.image}"/></td>
			      <td>${product.name}</td>
			      <td>${product.deletedAt}</td>
			      <td>${product.categoryId}</td>
			      <td>
			      	<a class="btn-link" href="/admin/product/${product.id}/detail">Chi tiết</a> | 
			      	<a type="submit" title="${product.id}" class="btn-link btn-restore" href="">Khôi phục</a> | 
			      	<a type="submit" title="${product.id}" class="btn-link btn-delete" href="">Xóa</a>
			      </td>
			    </tr>
		    </c:forEach>
		  </tbody>
		</table>
		<div>
			<nav aria-label="Page navigation example">
			  <ul class="pagination justify-content-end">
			  	<li class="page-item">
			      <a class="page-link" href="/admin/product/display/first"><<</a>
			    </li>
			    <li class="page-item">
			      <a class="page-link" href="/admin/product/display/prev"><</a>
			    </li>
			    <c:forEach varStatus="loop" var="page" begin="1" end="${pageCountTrash}">
				    <li class="page-item handlers"><a class="page-link" href="/admin/product/display/${loop.count}">${loop.count}</a></li>
			    </c:forEach>
			    <li class="page-item">
			      <a class="page-link" href="/admin/product/display/next">></a>
			    </li>
			    <li class="page-item">
			      <a class="page-link" href="/admin/product/display/last">>></a>
			    </li>
			  </ul>
			</nav>
		</div>	
	</div>
	
</form>

<script>
	var $ = document.querySelector.bind(document);
	var $$ = document.querySelectorAll.bind(document);
	
	var btnDelete = Array.from($$('.btn-delete'));
	var btnRestore = Array.from($$('.btn-restore'));
	
	
	//-------------------------------------------tạo phương thức xóa một sản phẩm------------------------------------------------------------------------------------------//
	async function delete_data(data){
		await fetch('/admin/product/'+data.id+'/delete-handler',{
			method: 'DELETE',
			headers:{
				'Content-Type': 'application/json',
				'Accept': 'application/json'
			},
			body: JSON.stringify(data)
		})
		.then(response => response.json())
		.then(data => console.log(data));
	}
	
	
	
	//-------------------------------------------Xử lý nút nhấn xóa một sản phẩm------------------------------------------------------------------------------------------//
	btnDelete.forEach((element)=>{
		element.addEventListener('click', (e)=>{
			e.preventDefault();
			var thisRow = Array.from($$('#row-table'));
			var res = confirm('Bạn có muốn xóa vĩnh viễn sản phẩm này??')
			
			if (res) {
				var productId = element.title
				var data = {'id': productId}
				delete_data(data);
				setTimeout(()=>{
					location.reload()
				}, 500);
			} else{
				return;
			}
		})	
	})
	
	
	
	//-------------------------------------------Xử lý checkbox parent------------------------------------------------------------------------------------------//
	var checkboxAll = $('#checkbox-all')
	var checkboxElements = Array.from($$('#checkbox-element'))
	var btnDeleteAll = $('.btn-delete-all')
	var btnRestoreAll = $('.btn-restore-all')
	
	checkboxAll.addEventListener('change', (e)=>{
		checkboxElements.forEach((element)=>{
			var resCheckboxAll = checkboxAll.checked
			element.checked = resCheckboxAll ? true : false
		})
		if (checkboxAll.checked) {
			btnDeleteAll.removeAttribute('hidden')
			btnRestoreAll.removeAttribute('hidden')
		} else{
			btnDeleteAll.setAttribute('hidden', 'hidden')
			btnRestoreAll.setAttribute('hidden', 'hidden')
		}
	})
	
	
	
	//-------------------------------------------Xử lý checkbox child all------------------------------------------------------------------------------------------//
	checkboxElements.forEach((element)=>{
		var countPrev = checkboxElements.length;
		element.addEventListener('change', (e)=>{
			var countCur = $('#row-table input[type="checkbox"]:checked').length;
			checkboxAll.checked = countCur != countPrev ? false : true;
			if (countCur > 0) {
				btnDeleteAll.removeAttribute('hidden')
				btnRestoreAll.removeAttribute('hidden')
			} else{
				btnDeleteAll.setAttribute('hidden', 'hidden')
				btnRestoreAll.setAttribute('hidden', 'hidden')
			}
		})
	})
	
	
	
	//-------------------------------------------tạo phương thức xóa nhiều sản phẩm------------------------------------------------------------------------------------------//
	async function delete_multi_data(data){
		await fetch('/admin/product/delete-multi-handler',{
			method: 'DELETE',
			headers:{
				'Content-Type': 'application/json',
				'Accept': 'application/json'
			},
			body: JSON.stringify(data)
		})
		.then(response => response.json())
		.then(data => console.log(data));
	}
	
			/* var formData = new FormData($('#form-data'))
			var arrProductId = formData.getAll('ids')
			var data = {'ids': arrProductId} */
		
	
			
	//-------------------------------------------Xử lý nút nhấn xóa nhiều sản phẩm------------------------------------------------------------------------------------------//
	btnDeleteAll.addEventListener('click', (e)=>{
		e.preventDefault();
		var res = confirm('Bạn có muốn xóa những sản phẩm này không??')
		
		if (res) {
			var arrProductId = []
			var ids = Array.from($$('#row-table input[type="checkbox"]:checked'))
			ids.forEach((element)=>{
				arrProductId.push(element.value)
			})
			var data = {'ids': arrProductId}
			console.log(data)
			delete_multi_data(data);
			setTimeout(()=>{
				location.reload()
			}, 500);
			
		} else{
			return;
		}
	})
	
	
	
	//-------------------------------------------tạo phương thức khôi phục một sản phẩm------------------------------------------------------------------------------------------//
	async function restore_data(data){
		await fetch('/admin/product/'+data.id+'/restore-handler',{
			method: 'PATCH',
			headers:{
				'Content-Type': 'application/json',
				'Accept': 'application/json'
			},
			body: JSON.stringify(data)
		})
		.then(response => response.json())
		.then(data => console.log(data));
	}
	
	
	
	//-------------------------------------------Xử lý nút nhấn khôi phục một sản phẩm------------------------------------------------------------------------------------------//
	btnRestore.forEach((element)=>{
		element.addEventListener('click', (e)=>{
			e.preventDefault();
			var thisRow = Array.from($$('#row-table'));
			var res = confirm('Bạn có muốn khôi phục sản phẩm này??')
			
			if (res) {
				var productId = element.title
				var data = {'id': productId}
				restore_data(data);
				setTimeout(()=>{
					location.reload()
				}, 500);
			} else{
				return;
			}
		})	
	})
	
	
	//-------------------------------------------tạo phương thức khôi phục nhiều sản phẩm------------------------------------------------------------------------------------------//
	async function restore_multi_data(data){
		await fetch('/admin/product/restore-multi-handler',{
			method: 'PATCH',
			headers:{
				'Content-Type': 'application/json',
				'Accept': 'application/json'
			},
			body: JSON.stringify(data)
		})
		.then(response => response.json())
		.then(data => console.log(data));
	}
	
			/* var formData = new FormData($('#form-data'))
			var arrProductId = formData.getAll('ids')
			var data = {'ids': arrProductId} */
		
	
			
	//-------------------------------------------Xử lý nút nhấn khôi phục nhiều sản phẩm------------------------------------------------------------------------------------------//
	btnRestoreAll.addEventListener('click', (e)=>{
		e.preventDefault();
		var res = confirm('Bạn có muốn khôi phục những sản phẩm này không??')
		
		if (res) {
			var arrProductId = []
			var ids = Array.from($$('#row-table input[type="checkbox"]:checked'))
			ids.forEach((element)=>{
				arrProductId.push(element.value)
			})
			var data = {'ids': arrProductId}
			console.log(data)
			restore_multi_data(data);
			setTimeout(()=>{
				location.reload()
			}, 500);
			
		} else{
			return;
		}
	})
	
	
	
</script>