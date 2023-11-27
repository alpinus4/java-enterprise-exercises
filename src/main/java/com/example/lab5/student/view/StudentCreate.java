package com.example.lab5.student.view;

import com.example.lab5.component.ModelFunctionFactory;
import com.example.lab5.student.StudentService;
import com.example.lab5.student.model.StudentCreateModel;
import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

@ConversationScoped
@Named
@Log
@NoArgsConstructor(force = true)
public class StudentCreate implements Serializable {
    private final StudentService studentService;

    private final ModelFunctionFactory modelFunctionFactory;

    @Getter
    private StudentCreateModel student;

    private final Conversation conversation;

    @Inject
    public StudentCreate(StudentService studentService, ModelFunctionFactory modelFunctionFactory, Conversation conversation) {
        this.studentService = studentService;
        this.modelFunctionFactory = modelFunctionFactory;
        this.conversation = conversation;
    }

    public void init() {
        if (conversation.isTransient()) {
            student = StudentCreateModel.builder()
                    .id(UUID.randomUUID())
                    .drunkenBeers(new ArrayList<>())
                    .build();
            conversation.begin();
        }
    }

    public String cancelAction() {
        conversation.end();
        return "/student/student_list.xhtml?faces-redirect=true";
    }

    public String saveAction() throws IOException {
        studentService.create(modelFunctionFactory.modelToStudent().apply(student));
        if(student.getAvatar() != null) {
            studentService.putAvatar(student.getId(), student.getAvatar().getInputStream());
        }
        conversation.end();
        return "/student/student_list.xhtml?faces-redirect=true";
    }

    /**
     * @return current conversation id
     */
    public String getConversationId() {
        return conversation.getId();
    }

    public String getCharacterPortraitUrl() {
        return "/view/api/v1/characters/new/portrait?cid=%s".formatted(getConversationId());
    }
}
