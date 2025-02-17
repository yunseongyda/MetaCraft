package com.metacraft.assetstore.Entities.Service;

import org.springframework.stereotype.Service;

import com.metacraft.assetstore.Entities.Repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
	private final CommentRepository commentRepo;
}
