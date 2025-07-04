package com.example.demo.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserCorrect;
import com.example.demo.entity.UserEntity;
import com.example.demo.excepciones.MiException;
import com.example.demo.repository.UserCorrectRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServicio {

	private static final Logger log = LogManager.getLogger(UserServicio.class);
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserCorrectRepository userCorrectRepository;

	@Transactional
	public void crearUsuario(int idusu, String user, String pass, String cor, String tip, int idusecor, String nom,
			String ape) throws MiException {

		// validar(idusu, user, pass, cor, tip, idusecor, nom, ape);

		UserEntity usuario = new UserEntity();

		usuario.setUser(user);
		usuario.setPass(pass);
		usuario.setCor(cor);
		usuario.setTip(tip);

		userRepository.save(usuario);

		UserCorrect usuariovalido = new UserCorrect();

		usuariovalido.setNom(nom);
		usuariovalido.setApe(ape);
		usuariovalido.setUsen(usuario);

		userCorrectRepository.save(usuariovalido);

	}

	public List<UserEntity> listarporusuarioregistrado() {

		List<UserEntity> usuarios = new ArrayList();

		usuarios = userRepository.findAll();

		return usuarios;

	}

	public List<UserCorrect> listarporusuariocorrectos() {

		List<UserCorrect> usuarioscorrectos = new ArrayList();

		usuarioscorrectos = userCorrectRepository.findAll();

		return usuarioscorrectos;

	}
	/*
	 * public Optional<UserEntity>getOne(Integer id){
	 * return userRepository.findById(id);
	 * }
	 * public UserEntity getOne(String id){
	 * return UserRepository.getOne(id);
	 * }
	 */

	/*
	 * public UserEntity getOne(Integer id){
	 * Optional<UserEntity> usuarioofi = userRepository.findById(id);
	 * usuarioofi =
	 * return UserRepository.findById(id);
	 * }
	 */

	public void eliminar(Integer idusu) {

		userCorrectRepository.deleteById(idusu);
		userRepository.deleteById(idusu);
	}

	@Transactional
	public void modificardatosdeusuario(int idusu, String user, String pass, String cor, String tip, int idusecor,
			String nom, String ape) throws MiException {

		validar(idusu, user, pass, cor, tip, idusecor, nom, ape);
		// Optional<UserEntity> respuestaUsuario = userRepository.findById(idusu);
		// Optional<UserCorrect> respuestaUsuarioCorrecto =
		// userCorrectRepository.findById(idusucorrect);

		UserEntity objUsuario = userRepository.findById(idusu)
				.orElseThrow(() -> new MiException("Registro no encontrado con ID: " + idusu));

		objUsuario.setCor(cor);
		objUsuario.setPass(pass);
		objUsuario.setTip(tip);
		objUsuario.setUser(user);

		UserCorrect usuariovalido = userCorrectRepository.findById(idusu)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idusu));

		usuariovalido.setApe(ape);
		usuariovalido.setNom(nom);

		userCorrectRepository.save(usuariovalido);

		userRepository.save(objUsuario);

		// Optional<UserCorrect> objCom = userCorrectRepository.findById(null);

	}

	private void validar(Integer idusu, String user, String pass, String cor, String tip, Integer idusecor, String nom,
			String ape) throws MiException {

		if (idusu == null) {
			throw new MiException("el idusu no puede ser nulo");
		}
		if (user.isEmpty() || user == null) {
			throw new MiException("el usuario no puede ser nulo o estar vacio");
		}
		if (pass.isEmpty() || pass == null) {
			throw new MiException("la contraseña no puede ser nulo o estar vacio");
		}
		if (cor.isEmpty() || cor == null) {
			throw new MiException("el correo no puede ser nulo o estar vacio");
		}
		if (tip.isEmpty() || tip == null) {
			throw new MiException("el tipo de usuario no puede ser nulo o estar vacio");
		}
		if (idusecor == null) {
			throw new MiException("el idusecor no puede ser nulo");
		}
		if (nom.isEmpty() || nom == null) {
			throw new MiException("el nombre no puede ser nulo o estar vacio");
		}
		if (ape.isEmpty() || ape == null) {
			throw new MiException("los apellidos no pueden ser nulo o estar vacio");
		}
	}

	public Optional<UserEntity> autenticar(String user, String pass) {
		List<UserEntity> usuarios = userRepository.findByUserContainingIgnoreCase(user);

		// Verifica si existe algún usuario con el nombre indicado
		if (!usuarios.isEmpty()) {
			for (UserEntity usuario : usuarios) {
				// Compara la contraseña con la almacenada
				if (usuario.getPass().equals(pass)) {
					return Optional.of(usuario);
				}
			}
		}
		return Optional.empty();
	}

	public String recuperarContrasena(String user) {
		Optional<UserEntity> usuario = userRepository.findByUserIgnoreCase(user);

		if (usuario.isPresent()) {
			return "Tu contraseña es: " + usuario.get().getPass();
		}
		return "Usuario no encontrado";
	}

	@Transactional
	public void modificarPassword(Integer idusu, String pass) throws MiException {

		UserEntity objUsuario = userRepository.findById(idusu)
				.orElseThrow(() -> new MiException("Registro no encontrado con ID: " + idusu));

		objUsuario.setPass(pass);

		userRepository.save(objUsuario);

	

	}

	/*
	 * public Optional<String> recuperarContrasena(String user) {
	 * List<UserEntity> usuarios =
	 * userRepository.findByUserContainingIgnoreCase(user);
	 * 
	 * if (!usuarios.isEmpty()) {
	 * // Retorna la primera contraseña encontrada
	 * return Optional.of(usuarios.get(0).getPass());
	 * }
	 * return Optional.empty();
	 * }
	 */

	/*
	 * public String generarTokenRecuperacion(String user) {
	 * List<UserEntity> usuarios =
	 * userRepository.findByUserContainingIgnoreCase(user);
	 * 
	 * if (!usuarios.isEmpty()) {
	 * // Genera un token único
	 * String token = UUID.randomUUID().toString();
	 * 
	 * // Aquí puedes guardar el token en la base de datos asociado al usuario
	 * // y enviar un correo con el enlace de restablecimiento.
	 * 
	 * return "http://tuservidor.com/restablecer?token=" + token;
	 * }
	 * return "Usuario no encontrado.";
	 * }
	 */
}
