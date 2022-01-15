package se.haris.booklender.service.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.haris.booklender.model.dto.LoanDTO;
import se.haris.booklender.service.converion.DTOService;
import se.haris.booklender.service.intefaces.LoanEntityService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class LoanServiceImpl implements se.haris.booklender.service.intefaces.LoanService {

    private final LoanEntityService loanEntityService;
    private final DTOService dtoService;

    @Override
    public LoanDTO create(LoanDTO dto) {
        return dtoService.toFullDTO(
                loanEntityService.create(dto)
        );
    }

    @Override
    public LoanDTO findById(String id) {
        return dtoService.toFullDTO(
                loanEntityService.findById(id)
        );
    }

    @Override
    public List<LoanDTO> findAll() {
        return loanEntityService.findAll().stream()
                .map(dtoService::toSmallDTO)
                .collect(Collectors.toList());

    }

    @Override
    public LoanDTO update(String id, LoanDTO dto) {
        return dtoService.toFullDTO(loanEntityService.update(id,dto));
    }

    @Override
    public boolean delete(String id) {
        return loanEntityService.delete(id);
    }

    @Override
    public List<LoanDTO> findByBookId(String id) {
        return loanEntityService.findByBook(id).stream()
                .map(dtoService::toSmallDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanDTO> findByUserId(String id) {
        return loanEntityService.findUserID(id).stream()
                .map(dtoService::toSmallDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanDTO> findByConcluded(boolean concluded) {
        return loanEntityService.findByConcluded(concluded).stream()
                .map(dtoService::toSmallDTO)
                .collect(Collectors.toList());
    }


}
