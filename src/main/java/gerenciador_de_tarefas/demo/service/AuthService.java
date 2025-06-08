package gerenciador_de_tarefas.demo.service;

import gerenciador_de_tarefas.demo.model.Usuario;
import gerenciador_de_tarefas.demo.repository.UsuarioRepository;
import gerenciador_de_tarefas.demo.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    public ResponseEntity<?> register(Usuario user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        usuarioRepository.save(user);
        return ResponseEntity.ok("Usuário registrado");
    }

    public ResponseEntity<?> login(Usuario login) {
        Usuario user = usuarioRepository.findByUsername(login.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        if (!new BCryptPasswordEncoder().matches(login.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha inválida");
        }
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(Map.of("token", token));
    }
}