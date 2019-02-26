package my.spring.project.springmvc.controller.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import my.spring.project.springmvc.domain.Cart;
import my.spring.project.springmvc.domain.CartItem;
import my.spring.project.springmvc.dto.CartDto;
import my.spring.project.springmvc.dto.CartItemDto;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CartMapper {

    public static CartDto cartToDto(final Cart cart) {
        if (cart == null) {
            return null;
        }

        final CartDto cartDto = new CartDto(cart.getId());

        final BigDecimal grandTotal = cart.getGrandTotal();
        cartDto.setGrandTotal(grandTotal);

        final List<CartItem> cartItems = cart.getCartItems();

        final List<CartItemDto> cartItemDtoList = cartItems == null ?
                Collections.emptyList() :
                cartItems.stream()
                        .map(CartMapper::cartItemToDto)
                        .collect(Collectors.toList());

        cartDto.setCartItems(cartItemDtoList);

        return cartDto;
    }

    public static Cart cartFromDto(final CartDto cartDto) {
        final Cart cart = new Cart(cartDto.getId());
        final List<CartItemDto> cartItemDtoList = cartDto.getCartItems();

        final List<CartItem> cartItems = cartItemDtoList == null ?
                Collections.emptyList() :
                cartItemDtoList.stream()
                        .map(CartMapper::cartItemFromDto)
                        .collect(Collectors.toList());

        cart.setCartItems(cartItems);

        return cart;
    }

    private static CartItemDto cartItemToDto(final CartItem cartItem) {
        if (cartItem == null) {
            return null;
        }

        final CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setId(cartItem.getId());
        cartItemDto.setName(cartItem.getProduct().getName());
        cartItemDto.setUnitPrice(cartItem.getProduct().getUnitPrice());

        final BigDecimal totalPrice = cartItem.getTotalPrice();
        cartItemDto.setTotalPrice(totalPrice);

        if (cartItem.getProduct() == null) {
            throw new IllegalArgumentException("CartItem should contain a Product");
        }

        cartItemDto.setProductId(cartItem.getProduct().getProductId());
        cartItemDto.setQuantity(cartItem.getQuantity());

        return cartItemDto;
    }

    private static CartItem cartItemFromDto(final CartItemDto cartItemDto) {
        if (cartItemDto == null) {
            return null;
        }

        final CartItem cartItem = new CartItem(cartItemDto.getId());
        cartItem.setProductId(cartItemDto.getProductId());
        cartItem.setQuantity(cartItemDto.getQuantity());

        return cartItem;
    }
}