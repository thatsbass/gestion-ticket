package com.workspace.bass.exceptions;

import java.time.LocalDateTime;
import java.util.Set;

public record ErrorResponse(String message, int code, LocalDateTime timestamp, Set<String> errors) { }
