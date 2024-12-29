package com.ajwalker.service;

import com.ajwalker.entity.PersonalDocument;
import com.ajwalker.repository.PersonalDocumentRepository;
import com.ajwalker.utility.Enum.user.EPosition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonalDocumentService {
    private final PersonalDocumentRepository personalDocumentRepository;

    public void createPersonalDocument(String personalRole, String name, String surname, String email, Long userId) {
        PersonalDocument personalDocument = PersonalDocument.builder()
                .email(email)
                .firstName(name)
                .lastName(surname)
                .position(statePersonalDocument(personalRole))
                .userId(userId)
                .build();
        personalDocumentRepository.save(personalDocument);
    }
    private EPosition statePersonalDocument(String personalRole) {
        if (personalRole == null) {
            return EPosition.NONE;
        }
        return switch (personalRole.toUpperCase()) {
            case "INTERN" -> EPosition.INTERN;
            case "JUNIOR" -> EPosition.JUNIOR;
            case "MID LEVEL" -> EPosition.MID_LEVEL;
            case "SENIOR" -> EPosition.SENIOR;
            case "TEAM LEAD" -> EPosition.TEAM_LEAD;
            case "MANAGER" -> EPosition.MANAGER;
            case "DIRECTOR" -> EPosition.DIRECTOR;
            default -> EPosition.NONE;
        };
    }

    public PersonalDocument findPersonalByUserId(Long userId) {
        return personalDocumentRepository.findById(userId).orElse(null);

    }
    
    public Optional<PersonalDocument> findByUserId(Long userId) {
        return personalDocumentRepository.findByUserId(userId);
    }

    public Map<Long, PersonalDocument> findPersonalDocumentByUserIds(List<Long> userIds) {
        return personalDocumentRepository.findAllByUserIds(userIds)
                .stream().collect(Collectors.toMap(
                        PersonalDocument::getId,
                        personalDocument -> personalDocument
                ));
    }


    public PersonalDocument save(PersonalDocument personalDocument) {
        return personalDocumentRepository.save(personalDocument);
    }

    public Map<Long, PersonalDocument> findByUserIdList(List<Long> employeeIds) {
        return personalDocumentRepository.findAllByUserIds(employeeIds).stream().collect(Collectors.toMap(
                PersonalDocument::getUserId,
                personalDocument -> personalDocument
        ));
    }
}