package com.techmanage.service;

import com.techmanage.dto.UserRequest;
import com.techmanage.exception.DuplicateEmailException;
import com.techmanage.model.UserType;
import com.techmanage.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    UserRepository repo;
    @InjectMocks
    UserService service;

    UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarUsuarioQuandoEmailDisponivel() {
        var req = new UserRequest("Ana","ana@ex.com","+55 11 99999-9999",
                LocalDate.parse("1990-01-01"),UserType.ADMIN);

        when(repo.existsByEmail(req.email())).thenReturn(false);
        when(repo.save(any())).thenAnswer(inv -> inv.getArgument(0));

        var resp = service.create(req);

        assertThat(resp.email()).isEqualTo("ana@ex.com");
        verify(repo).save(any());
    }

    @Test
    void deveFalharQuandoEmailExistir() {
        var req = new UserRequest("Ana","ana@ex.com","+55 11 99999-9999",
                LocalDate.parse("1990-01-01"),UserType.ADMIN);

        when(repo.existsByEmail(req.email())).thenReturn(true);

        assertThatThrownBy(() -> service.create(req))
                .isInstanceOf(DuplicateEmailException.class);
    }
}