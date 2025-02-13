package com.metacraft.store.Entities.Service;

import org.springframework.stereotype.Service;

import com.metacraft.store.Entities.Repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
	private final CommentRepository commentRepo;
}
