package com.springtutorials.api.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.springtutorials.model.ProductModel;
import com.springtutorials.service.ICategoryService;
import com.springtutorials.service.IProductService;

@RestController(value = "admin-product-api")
public class ProductAPI {
	
	@Autowired
	private IProductService productService;
	
	@Autowired 
	private ICategoryService categoryService;
	
	
	
	//-------------------------------------------hiển thị danh sách sản phẩm------------------------------------------------------------------------------------------//
	@GetMapping(value = {"/admin/product/display/{type}", "/admin/product/display"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ModelAndView displayListProduct(@PathVariable(required=false, value = "type") String type, Model model, HttpServletRequest req) {
		productService.handlerPagedListTrash(type, req);
		model.addAttribute("productsTrashCount", productService.findByDeleted(Boolean.TRUE).size());
		ModelAndView mav = new ModelAndView("admin/product/display");
		return mav;
	}
	
	
	
	//-------------------------------------------hiển thị chi tiết sản phẩm------------------------------------------------------------------------------------------//
	@GetMapping(value = "/admin/product/{id}/detail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ModelAndView detailListProduct(Model model, @PathVariable(value = "id") String id) {
		ModelAndView mav = new ModelAndView("admin/product/detail");
		model.addAttribute("product", productService.findById(id));
		return mav;
	}
	
	
	
	//-------------------------------------------hiển thị trang thêm mới sản phẩm------------------------------------------------------------------------------------------//
	@GetMapping(value = "/admin/product/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ModelAndView displayInsertProduct(Model model) {
		ModelAndView mav = new ModelAndView("admin/product/insert");
		model.addAttribute("products", productService.find());
		model.addAttribute("categoryListByParentId", categoryService.findByParentId(""));
		model.addAttribute("categoryListByChildId", categoryService.findByChildId(""));
		return mav;
	}
	
	
	
	//-------------------------------------------Xử lý thêm mới sản phẩm------------------------------------------------------------------------------------------//
	@PostMapping(value = "/admin/product/add-handler", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String handlerInsertProduct(@RequestBody ProductModel product) {
		productService.save(product);
		return "redirect:/admin/product/add";
	}
	
	
	
	//-------------------------------------------hiển thị trang chỉnh sửa sản phẩm------------------------------------------------------------------------------------------//
	@GetMapping(value = "/admin/product/{id}/edit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ModelAndView displayEditProduct(Model model, @PathVariable(value = "id") String id) {
		ModelAndView mav = new ModelAndView("admin/product/edit");
		ProductModel product = productService.findById(id);
		model.addAttribute("product", product);
		model.addAttribute("category", categoryService.findOneById(product.getCategoryId()));
		model.addAttribute("categoryListByParentId", categoryService.findByParentId(""));
		model.addAttribute("categoryListByChildId", categoryService.findByChildId(""));
		return mav;
	}
	
	
	
	//-------------------------------------------Xử lý cập nhập sản phẩm------------------------------------------------------------------------------------------//
	@PutMapping(value = "/admin/product/update-handler" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ModelAndView updateProduct(@RequestBody ProductModel product) {
		ModelAndView mav = new ModelAndView("admin/product/edit");
		productService.updateOne(product);
		return mav;
	}
	
	
	
	//-------------------------------------------Xử lý XÓA MỀM một sản phẩm------------------------------------------------------------------------------------------//
	@PatchMapping(value = "/admin/product/{id}/soft-delete-handler", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ModelAndView softDeleteProduct(@PathVariable(value = "id") String id) {
		productService.updateOneDeletedByID(id, Boolean.TRUE);
		ModelAndView mav = new ModelAndView("redirect:/admin/product");
		return mav;
	}
	
	
	
	//-------------------------------------------Xử lý XÓA MỀM nhiều sản phẩm------------------------------------------------------------------------------------------//
	@PatchMapping(value = "/admin/product/soft-delete-multi-handler", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ModelAndView softDeleteMultilProduct(@RequestBody ProductModel product) {
		productService.updateManyDeletedByID(product.getIds(), Boolean.TRUE);
		ModelAndView mav = new ModelAndView("redirect:/admin/product");
		return mav;
	}
	

	
	
	//-------------------------------------------Xử lý xóa một sản phẩm------------------------------------------------------------------------------------------//
	@PatchMapping(value = "/admin/product/{id}/delete-handler", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ModelAndView deleteProduct(@PathVariable(value = "id") String id) {
		productService.deleteOne(id);
		ModelAndView mav = new ModelAndView("redirect:/admin/product");
		return mav;
	}
	
	
	
	//-------------------------------------------Xử lý xóa nhiều sản phẩm------------------------------------------------------------------------------------------//
	@PatchMapping(value = "/admin/product/delete-multi-handler", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ModelAndView deleteMultilProduct(@RequestBody ProductModel product) {
		productService.deleteMany(product.getIds());
		ModelAndView mav = new ModelAndView("redirect:/admin/product");
		return mav;
	}
	
	
	
	//-------------------------------------------Xử lý khôi phục một sản phẩm------------------------------------------------------------------------------------------//
	@PatchMapping(value = "/admin/product/{id}/restore-handler", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ModelAndView restoreProduct(@PathVariable(value = "id") String id) {
		productService.updateOneDeletedByID(id, Boolean.FALSE);
		ModelAndView mav = new ModelAndView("redirect:/admin/product");
		return mav;
	}
		
		
		
	//-------------------------------------------Xử lý khôi phục nhiều sản phẩm------------------------------------------------------------------------------------------//
	@PatchMapping(value = "/admin/product/restore-multi-handler", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ModelAndView restoreMultilProduct(@RequestBody ProductModel product) {
		productService.updateManyDeletedByID(product.getIds(), Boolean.FALSE);
		ModelAndView mav = new ModelAndView("redirect:/admin/product");
		return mav;
	}
}
