package org.laqqueta.widetechinterview.service;

import org.laqqueta.widetechinterview.model.Product;
import org.laqqueta.widetechinterview.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Integer id, Product product) {
        if (!productRepository.existsById(id))
            throw new RuntimeException("Product Doesn't Exist.");

        Product productUpdate = getProduct(id);
        productUpdate.setName(product.getName() == null ? productUpdate.getName() : product.getName());
        productUpdate.setType(product.getType() == null ? productUpdate.getType() : product.getType());
        productUpdate.setPrice(product.getPrice() == null ? productUpdate.getPrice() : product.getPrice());
        productUpdate.setQuantity(product.getQuantity() == null ? productUpdate.getQuantity() : product.getQuantity());
        productRepository.save(productUpdate);
        return productUpdate;
    }

    public boolean deleteProduct(Integer id) {
        if (!productRepository.existsById(id)) {
            return false;
        }

        productRepository.deleteById(id);
        return true;
    }

    public Product getProduct(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Doesn't Exist."));
    }

    public Page<Product> getProducts(Pageable pageable) {
        return productRepository
                .findAll(pageable);
    }

}
