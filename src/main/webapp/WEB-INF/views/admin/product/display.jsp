<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/taglib.jsp"%>
<form class="container mt-4" id='form-data'> 
	<div class="row">
		<h3 class="btn-link">Danh sách sản phẩm</h3>
	</div>
	<hr>
	<div class="row">
		<a class="btn-link" href="/admin/product/add">Thêm mới sản phẩm</a>
		<button class="ml-4 btn btn-outline-danger btn-sm btn-delete-all" hidden>Xóa</button>
		<table class="table mt-4">
		  <thead>
		    <tr>
		      <th scope="col"><input type="checkbox" id="checkbox-all"></th>
		      <th scope="col">#</th>
		      <th scope="col">ID</th>
		      <th scope="col">Ảnh</th>
		      <th scope="col">Tên</th>
		      <th scope="col">Số lượng</th>
		      <th scope="col">Đơn giá</th>
		      <th scope="col">ID danh mục</th>
		      <th scope="col" class="text-center">Công cụ</th>
		    </tr>
		  </thead>
		  <tbody>
		    <c:forEach varStatus="loop" var="product" items="${products}">
		    	<tr id="row-table">
		    	  <th><input type="checkbox" id="checkbox-element" value="${product.id}"></th>
			      <th scope="row">${loop.count}</th>
			      <td>${product.id}</td>
			      <td><img style="width: 100px;" src="${product.image}"/></td>
			      <td>${product.name}</td>
			      <td>${product.quantity}</td>
			      <td>${product.price}</td>
			      <td>${product.categoryId}</td>
			      <td>
			      	<a class="btn-link" href="/admin/product/${product.id}">Chi tiết</a> | 
			      	<a class="btn-link" href="/admin/product/${product.id}/edit">Sửa</a> | 
			      	<button type="submit" title="${product.id}" class="btn btn-link btn-delete">Xóa</button>
			      </td>
			    </tr>
		    </c:forEach>
		  </tbody>
		</table>	
	</div>
	
</form>

<script>
	var $ = document.querySelector.bind(document);
	var $$ = document.querySelectorAll.bind(document);
	
	var btnDelete = Array.from($$('.btn-delete'));
	
	
	
	
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
	
	
	
	btnDelete.forEach((element, index)=>{
		element.addEventListener('click', (e)=>{
			e.preventDefault();
			var thisRow = Array.from($$('#row-table'));
			var res = confirm('Bạn có muốn xóa sản phẩm này??')
			
			if (res) {
				var productId = element.title
				var data = {'id': productId}
				delete_data(data);
				thisRow[index].setAttribute('hidden', 'hidden')
			} else{
				return;
			}
		})
	})
	
	
	
	var checkboxAll = $('#checkbox-all')
	var checkboxElements = Array.from($$('#checkbox-element'))
	var btnDeleteAll = $('.btn-delete-all')
	
	checkboxAll.addEventListener('change', (e)=>{
		checkboxElements.forEach((element)=>{
			var resCheckboxAll = checkboxAll.checked
			element.checked = resCheckboxAll ? true : false
		})
		if (checkboxAll.checked) {
			btnDeleteAll.removeAttribute('hidden')
		} else{
			btnDeleteAll.setAttribute('hidden', 'hidden')
		}
	})
	
	checkboxElements.forEach((element)=>{
		var countPrev = checkboxElements.length;
		element.addEventListener('change', (e)=>{
			var countCur = $('#row-table input[type="checkbox"]:checked').length;
			checkboxAll.checked = countCur != countPrev ? false : true;
			if (countCur > 0) {
				btnDeleteAll.removeAttribute('hidden')
			} else{
				btnDeleteAll.setAttribute('hidden', 'hidden')
			}
		})
	})
	
	
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
			
			var rows = Array.from($$('#row-table'));
			ids.forEach((element, index)=>{
				rows[index].setAttribute('hidden', 'hidden')
			})
		} else{
			return;
		}
	})
	
</script>