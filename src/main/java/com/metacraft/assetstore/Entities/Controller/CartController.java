package com.metacraft.assetstore.Entities.Controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.metacraft.assetstore.Entities.Cart;
import com.metacraft.assetstore.Entities.SiteUser;
import com.metacraft.assetstore.Entities.Service.CartService;
import com.metacraft.assetstore.Entities.Service.ProductService;
import com.metacraft.assetstore.Entities.Service.SiteUserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
  private final SiteUserService userService;
  private final CartService cartService;
  private final ProductService productService;
  //장바구니 페이지로 이동하는 메소드
  @PreAuthorize("isAuthenticated()")
  @GetMapping("/list")
  public String cart(Principal principal, Model model) {

    SiteUser user = userService.getSiteUser(principal.getName());
    Cart cart = cartService.getCart(user.getId());

    if (cart == null) {
      cart = cartService.createCart(user.getId());
    }
    model.addAttribute("assetList", cart.getAsset());
    model.addAttribute("total", 0);
    return "cart";
  } 
  @PreAuthorize("isAuthenticated()")
  @GetMapping("/add/{p_id}")
  public String addAssetToCart(@PathVariable("p_id") Integer id, Principal principal) {

    SiteUser user = userService.getSiteUser(principal.getName());
    Cart cart = cartService.getCart(user.getId());
    if (cart == null) {
      cart = cartService.createCart(user.getId());
    }
    cartService.addAssetToCart(cart, productService.getProduct(id));
    return "redirect:/cart/list";
  }
  @PreAuthorize("isAuthenticated()")
  @GetMapping("/remove/{p_id}")
  public String deleteAssetFromCart(@PathVariable("p_id") Integer id, Principal principal) {

    SiteUser user = userService.getSiteUser(principal.getName());
    Cart cart = cartService.getCart(user.getId());
    if (cart == null) {
      cart = cartService.createCart(user.getId());
    }
    cartService.deleteAssetFromCart(cart, productService.getProduct(id));
    return "redirect:/cart/list";
  }
}
