segment .data
a:	dq	1000
cha:	db	65
b:	dq	100
z:	dd	500
__strlit1:    db "Initialized", 0
c:	dq	__strlit1
__strlit2:    db "%d", 10, 0
__strlit3:    db "%d", 10, 0
__strlit4:    db "%d", 10, 0
__strlit5:    db "%c", 10, 0
__strlit6:    db "%c", 10, 0
__strlit7:    db "%s", 10, 0
__strlit8:    db "Override c", 10, 0
__strlit9:    db "%s", 10, 0

segment .text
	extern clearerr
	extern fclose
	extern feof
	extern ferror
	extern fflush
	extern fgetc
	extern fgetpos
	extern fgets
	extern fopen
	extern fprintf
	extern fputc
	extern fputs
	extern fread
	extern freopen
	extern fscanf
	extern fseek
	extern fsetpos
	extern ftell
	extern fwrite
	extern getc
	extern getchar
	extern gets
	extern perror
	extern printf
	extern putc
	extern putchar
	extern puts
	extern remove
	extern rename
	extern rewind
	extern scanf
	extern setbuf
	extern setvbuf
	extern snprintf
	extern sprintf
	extern sscanf
	extern tmpfile
	extern tmpnam
	extern ungetc
	extern vfprintf
	extern vfscanf
	extern vprintf
	extern vscanf
	extern vsnprintf
	extern vsprintf
	extern vsscanf

	global    main
main:
	push	rbp
	mov	rbp, rsp

	sub	rsp, 17
	mov	dword [rbp-9], edi
	mov	qword [rbp-17], rsi
	mov	rdi, __strlit2
	mov	rsi, qword [b]
	mov	eax, 0
	call	printf
	mov	dword [rbp-4], 1000
	lea	rax, [rbp-4]
	mov	qword [b], rax
	mov	rax, qword [b]
	mov	dword [rax], 500
	mov	rdi, __strlit3
	mov	rax, qword [b]
	mov	esi, dword [rax]
	mov	eax, 0
	call	printf
	mov	rax, qword [b]
	add	dword [rax], 200
	mov	eax, dword [rax]
	add	dword [z], eax
	mov	rdi, __strlit4
	mov	esi, dword [z]
	mov	eax, 0
	call	printf
	mov	rdi, __strlit5
	movsx	esi, byte [cha]
	mov	eax, 0
	call	printf
	mov	byte [rbp-5], 66
	mov	rdi, __strlit6
	movsx	esi, byte [rbp-5]
	mov	eax, 0
	call	printf
	mov	rdi, __strlit7
	mov	rsi, qword [c]
	mov	eax, 0
	call	printf
	mov	rax, __strlit8
	mov	qword [c], rax
	mov	rdi, __strlit9
	mov	rsi, qword [c]
	mov	eax, 0
	call	printf
	mov	eax, 0
	jmp	mainExit
mainExit:
	mov	rsp,rbp                                 
	pop	rbp                                 
	ret


segment .bss
