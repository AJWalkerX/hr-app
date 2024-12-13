package com.ajwalker.service;

import com.ajwalker.entity.PersonalDocument;
import com.ajwalker.repository.PersonalDocumentRepository;
import com.ajwalker.utility.Enum.user.EPosition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}

