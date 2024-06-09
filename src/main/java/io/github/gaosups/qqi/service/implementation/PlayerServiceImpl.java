package io.github.gaosups.qqi.service.implementation;

import io.github.gaosups.qqi.model.Player;
import io.github.gaosups.qqi.model.dto.PlayerDTO;
import io.github.gaosups.qqi.model.dto.mapper.PlayerDTOMapper;
import io.github.gaosups.qqi.model.exceptions.PlayerNotFoundException;
import io.github.gaosups.qqi.repository.PlayerRepository;
import io.github.gaosups.qqi.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class PlayerServiceImpl
	implements PlayerService {

	private final PlayerRepository playerRepository;
	private final MessageSource messageSource;

	@Override
	@Transactional(readOnly = true)
	public List<PlayerDTO> findAll() {
		Stream<Player> users =
			StreamSupport.stream(playerRepository.findAll()
				                     .spliterator(), false);

		return users.map(PlayerDTOMapper.createMapper()::withPlayer)
			       .map(PlayerDTOMapper::build)
			       .toList();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PlayerDTO> findById(final UUID id) {
		return playerRepository.findById(id)
			       .map(PlayerDTOMapper.createMapper()::withPlayer)
			       .map(PlayerDTOMapper::build);
	}
	@Override
	@Transactional
	public Player save(final Player player) {
		return playerRepository.save(player);
	}

	@Override
	@Transactional
	public void updateUserById(final UUID id, final Player player) {
		playerRepository.findById(id)
			.ifPresentOrElse(
				player1 -> player.setUsername(player1.getUsername()),
				() -> {
					throw new PlayerNotFoundException(
						messageSource.getMessage(
							"user.message.notfound",
							new String[] { id.toString() },
							Locale.US)
					);
				}
			);
	}

	@Override
	@Transactional
	public void deleteById(final UUID id) {
		playerRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void delete(final Player player) {
		playerRepository.delete(player);
	}
}
