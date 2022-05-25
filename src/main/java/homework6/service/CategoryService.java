package homework6.service;

import homework6.entites.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import homework6.repositories.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> getOneById(Long id) {
        return categoryRepository.findById(id);
    }

    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }
}
