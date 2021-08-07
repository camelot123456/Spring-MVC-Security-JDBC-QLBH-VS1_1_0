<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/taglib.jsp"%>
<div class="container mt-4">
	<div class="row">
		<h3 class="btn-link">Trang chi tiết</h3>
	</div>
	<hr>
  <div class="row mt-4">
    <div class="col-4">
      <img style="width: 350px;" alt="" src="${product.image}">
    </div>
    <div class="col-8">
      <h3>${product.name}</h3>
      <p>Mã sản phẩm: ${product.id}</p>
      <p>Mô tả: ${product.description}</p>
      <p>Số lượng: ${product.quantity}</p>
      <p>Đơn giá: ${product.price}</p>
      <p>Mã danh mục: ${product.categoryId}</p>
      </div>
  	</div>
  	<hr>
  	<div class="row">
	  	<div class="col-6">
	      <p>Tên phụ: ${product.slug}</p>
	      <p>Ngày tạo: ${product.createdAt}</p>
	      <p>Người tạo: ${product.createdBy}</p>
	      <p>Ngày cập nhập: ${product.modifiedAt}</p>
	    </div>
	    <div class="col-6">
	      <p>Người cập nhập: ${product.modifiedBy}</p>
	      <p>Ngày xóa: ${product.deletedAt}</p>
	      <p>Kiểm tra đã xóa: ${product.deleted}</p>
	      <p>Trang thái: ${product.status}</p>
	    </div>
    </div>
</div>
