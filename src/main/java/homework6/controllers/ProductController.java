package homework6.controllers;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import homework6.dto.ProductDto;
import homework6.entites.Product;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import homework6.exceptions.MarketError;
import homework6.exceptions.ResourceNotFoundException;
import homework6.repositories.ProductRepository;
import homework6.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@Api("Set of endpoints for products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;

    @GetMapping
    @ApiOperation("Returns products")
    public List<ProductDto> getProducts() {
        return productService.getAllProducts().stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ApiOperation("Returns a specific product by their identifier. 404 if does not exist.")
    public ProductDto getProductById(@ApiParam("Id of the book to be obtained. Cannot be empty.") @PathVariable Long id) {
        Product p = productService.getOneById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + id));
        return new ProductDto(p);
    }

    @PostMapping
    @ApiOperation("Creates a new product. If id != null, then throw bad request response")
    public ResponseEntity<?> createNewProduct(@RequestBody ProductDto p) {
        if (p.getId() != null) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Id must be null for new entity"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ProductDto(productService.save(p)), HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation("Modify product")
    public ResponseEntity<?> modifyProduct(@RequestBody ProductDto p) {
        if (p.getId() == null) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Id must be not null for new entity"), HttpStatus.BAD_REQUEST);
        }
        if (!productService.existsById(p.getId())) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Product with id: " + p.getId() + " doesn't exist"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ProductDto(productService.save(p)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete product")
    public void deleteById(@ApiParam("Id of the product") @PathVariable Long id) {
        productService.deleteById(id);
    }
}
