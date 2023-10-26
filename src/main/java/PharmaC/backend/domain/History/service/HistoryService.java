package PharmaC.backend.domain.History.service;

import PharmaC.backend.domain.History.domain.History;
import PharmaC.backend.domain.History.dto.HistoryDTO;
import PharmaC.backend.domain.History.dto.request.AddHistoryRequestDTO;
import PharmaC.backend.domain.History.dto.request.UpdateHistoryRequestDTO;
import PharmaC.backend.domain.History.exception.HistoryNotFound;
import PharmaC.backend.domain.History.mapper.HistoryMapper;
import PharmaC.backend.domain.History.repository.HistoryRepository;
import PharmaC.backend.domain.User.domain.User;
import PharmaC.backend.domain.User.exception.UserNotFound;
import PharmaC.backend.domain.User.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final HistoryMapper historyMapper;
    private final UserRepository userRepository;

    @Transactional
    public HistoryDTO createHistory(AddHistoryRequestDTO addHistoryRequestDTO, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> { throw UserNotFound.EXCEPTION; });
        History newHistory = historyMapper.toEntity(addHistoryRequestDTO.getHistoryVo(), user);
        historyRepository.save(newHistory);
        return HistoryDTO.entityToDto(newHistory);
    }

    @Transactional(readOnly = true)
    public List<HistoryDTO> getAllHistory(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> { throw UserNotFound.EXCEPTION; });
        List<History> historyList = historyRepository.findAllByUser(user);
        List<History> sortedHistoryList = historyMapper.sortHistory(historyList);
        return historyMapper.toDtoList(sortedHistoryList);
    }

    @Transactional
    public HistoryDTO updateHistory(UpdateHistoryRequestDTO updateHistoryRequestDTO, Long historyId) {
        History history = historyRepository.findById(historyId).orElseThrow(() -> { throw HistoryNotFound.EXCEPTION; });
        history.update(updateHistoryRequestDTO);
        return HistoryDTO.entityToDto(history);
    }

}
