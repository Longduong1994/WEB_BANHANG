package ra.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import ra.exception.LoginException;
import ra.exception.RegisterException;
import ra.model.dto.request.ForgotPasswordForm;
import ra.model.dto.request.LoginRequestDto;
import ra.model.dto.request.RegisterRequestDto;
import ra.model.dto.request.UpdateReviewRequestDto;
import ra.model.dto.response.*;
import ra.model.entity.Cart;
import ra.model.entity.Users;
import ra.security.jwt.JwtProvider;
import ra.security.user_principle.UserDetailService;
import ra.security.user_principle.UserPrinciple;
import ra.service.impl.cart.ICartService;
import ra.service.impl.order.IOrderService;
import ra.service.impl.review.IReviewService;
import ra.service.impl.user.IUserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

;

@RestController
@RequestMapping("/api/project/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private IUserService userService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private ICartService cartService;
    @Autowired
    private IReviewService reviewService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequestDto registerRequestDto) throws RegisterException {
        userService.save(registerRequestDto);
        return new ResponseEntity<>("SUSSES",HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody @Valid LoginRequestDto loginRequestDto, HttpSession session) throws LoginException {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new  UsernamePasswordAuthenticationToken(
                    loginRequestDto.getUsername(),loginRequestDto.getPassword()));
        }catch (AuthenticationException e) {
            e.printStackTrace();
            throw new LoginException("Username or password is incorrect!");
        }
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        if (userPrinciple.isStatus()== false){
            throw new LoginException("Account is locked");
        }
        String token = jwtProvider.generateToken(userPrinciple);

        List<String> roles = userPrinciple.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        List<Cart> carts = new ArrayList<Cart>();
        session.setAttribute("carts",carts);
        return ResponseEntity.ok(JwtResponse.builder().token(token)
                .fullName(userPrinciple.getFullName())
                .username(userPrinciple.getUsername())
                .roles(roles)
                .type("Bearer")
                .status(userPrinciple.isStatus()).build());
    }

    @PostMapping("/cancel/{id}")
    private ResponseEntity<String> cancel(@PathVariable Long id,Authentication authentication){
        UserPrinciple userPrinciple =null;
        try {
            userPrinciple = (UserPrinciple) authentication.getPrincipal();
        }catch (Exception e){
            throw new ClassCastException("You must be logged in to cancel");
        }
        Long userId = (Long) userPrinciple.getId();
        orderService.cancel(id,userId);
        return new ResponseEntity<String>("Cancelled",HttpStatus.OK);
    }

    @GetMapping("/view/order")
    public ResponseEntity<List<OrderResponseDto>> getOrder(Authentication authentication){
        UserPrinciple userPrinciple =null;
        try {
            userPrinciple = (UserPrinciple) authentication.getPrincipal();
        }catch (Exception e){
            throw new ClassCastException("You must be logged in to cancel");
        }
        Long userId = (Long) userPrinciple.getId();
        return new ResponseEntity<>(orderService.viewOrder(userId),HttpStatus.OK);
    }

    @GetMapping("/order/detail/{id}")
    private ResponseEntity<List<CartResponseDto>> orderDetail(@PathVariable Long id) {
        return new ResponseEntity<>(cartService.findAllByOrder(id),HttpStatus.OK);
    }

    @PutMapping("/forget")
    public ResponseEntity<UserResponseDto> changePassword(@RequestBody ForgotPasswordForm forgotPasswordForm){
        return new ResponseEntity<>(userService.changePassword(forgotPasswordForm),HttpStatus.OK);
    }

    @GetMapping("/review")
    public ResponseEntity<List<ReviewResponseDto>> getReview(Authentication authentication){
        UserPrinciple userPrinciple =null;
        try {
            userPrinciple = (UserPrinciple) authentication.getPrincipal();
        }catch (Exception e){
            throw new ClassCastException("You must be logged in to cancel");
        }
        Long userId = (Long) userPrinciple.getId();
        return new ResponseEntity<>(reviewService.findReviewByOrder(userId),HttpStatus.OK);
    }

    @PutMapping("/review/{id}")
    public ResponseEntity<ReviewResponseDto> updateReview(@PathVariable Long id, @RequestBody UpdateReviewRequestDto updateReviewRequestDto,Authentication authentication){
        UserPrinciple userPrinciple =null;
        try {
            userPrinciple = (UserPrinciple) authentication.getPrincipal();
        }catch (Exception e){
            throw new ClassCastException("You must be logged in to cancel");
        }
        Long userId = (Long) userPrinciple.getId();
        return new ResponseEntity<>(reviewService.update(updateReviewRequestDto,id,userId),HttpStatus.OK);
    }

    @DeleteMapping("/review/{id}")
    public ResponseEntity<ReviewResponseDto> deleteReview(@PathVariable Long id,Authentication authentication){
        UserPrinciple userPrinciple =null;
        try {
            userPrinciple = (UserPrinciple) authentication.getPrincipal();
        }catch (Exception e){
            throw new ClassCastException("You must be logged in to cancel");
        }
        Long userId = (Long) userPrinciple.getId();
        return new ResponseEntity<>(reviewService.delete(id, userId),HttpStatus.OK);
    }


}
