package com.springtutorials.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping(value = "/admin/product", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ModelAndView displayListProduct(Model model) {
		ModelAndView mav = new ModelAndView("admin/product/display");
		model.addAttribute("products", productService.find());
		return mav;
	}
	
	@GetMapping(value = "/admin/product/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ModelAndView detailListProduct(Model model, @PathVariable(value = "id") String id) {
		ModelAndView mav = new ModelAndView("admin/product/detail");
		model.addAttribute("product", productService.findById(id));
		return mav;
	}
	
	@GetMapping(value = "/admin/product/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ModelAndView displayInsertProduct(Model model) {
		ModelAndView mav = new ModelAndView("admin/product/insert");
		model.addAttribute("products", productService.find());
		model.addAttribute("categoryListByParentId", categoryService.findByParentId(""));
		model.addAttribute("categoryListByChildId", categoryService.findByChildId(""));
		return mav;
	}
	
	@PostMapping(value = "/admin/product/add-handler", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String handlerInsertProduct(@RequestBody ProductModel product) {
		productService.save(product);
		return "redirect:/admin/product/add";
	}
	
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
	
	@PutMapping(value = "/admin/product/update-handler" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ModelAndView updateProduct(@RequestBody ProductModel product) {
		ModelAndView mav = new ModelAndView("admin/product/edit");
		productService.updateOne(product);
		return mav;
	}
	
	@DeleteMapping(value = "/admin/product/{id}/delete-handler", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ModelAndView deleteProduct(@PathVariable(value = "id") String id) {
		productService.deleteOne(id);
		ModelAndView mav = new ModelAndView("redirect:/admin/product");
		return mav;
	}
	
	@DeleteMapping(value = "/admin/product/delete-multi-handler", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ModelAndView deleteMultilProduct(@RequestBody ProductModel product) {
		productService.deleteMany(product.getIds());
		ModelAndView mav = new ModelAndView("redirect:/admin/product");
		return mav;
	}
}
