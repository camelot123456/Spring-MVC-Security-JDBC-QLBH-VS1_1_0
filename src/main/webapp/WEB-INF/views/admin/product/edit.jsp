<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/taglib.jsp" %>
<div class="container mt-4">
	<h3>Thêm mới sản phẩm</h3>
	<div class="row">
		<form id="form-insert">
		  	<div class="form-group row">
		  		<label for="categoryIdc" class="col-sm-4 col-form-label">Mã danh mục cha</label>
			    <div class="col-sm-10">
			    	<input type="hidden" value="${category.parentId}" id="value-parentId">
			    	<select id="categoryIdc" class="form-control form-control-sm" required>
					  <option value="">---Chọn danh mục cha---</option>
					  
					  <c:forEach var="category" items="${categoryListByParentId}">
					  	<option class="option" value="${category.id}">${category.name}</option>
					  </c:forEach>
					</select>
			    </div>
			</div>
			
			<div class="form-group row">
		  		<label for="categoryId" class="col-sm-4 col-form-label">Mã danh mục con</label>
			    <div class="col-sm-10">
			    	<input type="hidden" value="${category.id}" id="value-childId">
			    	<select id="categoryId" name="categoryId" class="form-control form-control-sm" required>
					  <option value="">---Chọn danh mục con---</option>
					  <c:forEach var="category" items="${categoryListByChildId}">
					  	<option class="option" title="${category.parentId}" value="${category.id}">${category.name}</option>
					  </c:forEach>
					</select>
			    </div>
			</div>
		    
		  
		  <div class="form-group row">
		    <label for="id" class="col-sm-4 col-form-label">Mã sản phẩm</label>
		    <div class="col-sm-10">
		    	
		      <input type="text" class="form-control" id="id" name="id" placeholder="id"  value="${product.id}" required="required" >
		    </div>
		  </div>
		  
		  <div class="form-group row">
		    <label for="name" class="col-sm-4 col-form-label">Tên sản phẩm</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="name" name="name" placeholder="Tên sản phẩm" value="${product.name}" required="required">
		    </div>
		  </div>
		  
		  <div class="form-group row">
		    <label for="quantity" class="col-sm-4 col-form-label">Số lượng</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="quantity" name="quantity" placeholder="Số lượng" value="${product.quantity}" required="required">
		    </div>
		  </div>
		  
		  <div class="form-group row">
		    <label for="price" class="col-sm-4 col-form-label">Đơn giá</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="Đơn giá" name="price" placeholder="Đơn giá" value="${product.price}" required="required">
		    </div>
		  </div>
		  
		  <div class="form-group row">
		    <label for="description" class="col-sm-4 col-form-label">Mô tả</label>
		    <div class="col-sm-10">
		    	<textarea class="form-control" id="description" name="description" rows="3" value="${product.description}" required="required"></textarea>
		    </div>
		  </div>
		  
		  <div class="form-group row">
		    <label for="image" class="col-sm-4 col-form-label">Hình ảnh</label>
		    <div class="col-sm-10">
		      <input type="file" class="form-control-file" id="image">
		      <input type="text" class="form-control mt-2" id="image_input" name="image" placeholder="Hình ảnh" value="${product.image}" required="required">
		    </div>
		  </div>
		  
		  <button type="submit" class="btn btn-primary btn-add">Lưu lại</button>
		</form>
	</div>
</div>
<br  class="mb-4">


<!-- ------------------------------------Design Toast-------------------------------------  -->



<!-- ------------------------------------Xu li theo co che Client side render-------------------------------------  -->
<script>
	var $ = document.querySelector.bind(document);
	var $$ = document.querySelectorAll.bind(document);
	
	var formInsert = $('#form-insert');
	var btnAdd = $('.btn-add');
	var category = $('#categoryIdc');
	var categoryChild = $('#categoryId');
	var optionCategoryParent = Array.from($$('#categoryId .option'));
	
	/* ------------------------------------xu li cac danh muc------------------------------------- */
	
	var parentId = $('#value-parentId')
	var childId = $('#value-childId')
	
	category.value = parentId.value
	
	categoryChild.value = childId.value
	
	
	
	
	/* ------------------------------------xu li cac danh muc------------------------------------- */
	optionCategoryParent.forEach((element)=>{
		if (element.title != category.value) {
			element.setAttribute('hidden', 'hidden')
		} 
		else{
			element.removeAttribute('hidden')
		}
	})
	
	category.addEventListener('change', (e)=>{
		var parentId = category.value;
		
		if(parentId == ''){
			optionCategoryParent.forEach((element)=>{
				element.setAttribute('hidden', 'hidden')
			})
		}
		else {
			optionCategoryParent.forEach((element)=>{
				if (element.title != parentId) {
					element.setAttribute('hidden', 'hidden')
				} 
				else{
					element.removeAttribute('hidden')
				}
			})
		}
	})
	
	/* ------------------------------------phuong thuc sua data------------------------------------- */
	
	
	async function addData(data){
		await fetch('http://localhost:8080/admin/product/update-handler', {
			method: 'PUT',
			headers:{
				'Content-Type': 'application/json',
				'Accept': 'application/json'
			},
			body: JSON.stringify(data)
		})
			.then(response => response.json())
		    .then(data => console.log(data))
	}
	
	
	/* ------------------------------------xu li su kien sua data------------------------------------- */
	
	
	btnAdd.addEventListener('click', (e)=>{
		
		e.preventDefault();
		
		var formData = new FormData(formInsert);
		var categoryId = formData.get("categoryId");
		var id = formData.get("id");
		var name = formData.get("name");
		var quantity = formData.get("quantity");
		var price = formData.get("price");
		var image = formData.get("image");
		
		var data = {categoryId, id, name, quantity, price, image};
		
		console.log(data);
		addData(data);
		res = confirm('Bạn cập nhập sản phẩm thành công ^^')
		if(res) {location.reload()}
	});
	
	
	
</script>
    