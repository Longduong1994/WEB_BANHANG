package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.model.dto.request.PaymentRequestDto;
import ra.model.dto.response.PaymentResponseDto;
import ra.service.impl.payment.IPaymentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/project/payment")
public class PaymentController {
    @Autowired
    private IPaymentService paymentService;
    @GetMapping
    public ResponseEntity<List<PaymentResponseDto>> getPayment(){
        return new ResponseEntity<>(paymentService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDto> findPaymentById(@PathVariable Long id){
        return new ResponseEntity<>(paymentService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDto> addPayment(@RequestBody @Valid PaymentRequestDto paymentRequestDto){
        return new ResponseEntity<>(paymentService.save(paymentRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponseDto> updatePayment(@RequestBody @Valid PaymentRequestDto paymentRequestDto,@PathVariable Long id){
        return new ResponseEntity<>(paymentService.update(paymentRequestDto,id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<PaymentResponseDto> deletePayment(@PathVariable Long id){
        return new ResponseEntity<>(paymentService.delete(id), HttpStatus.OK);
    }
}
