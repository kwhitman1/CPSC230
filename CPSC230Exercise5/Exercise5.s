.data
N:					.word 2
error_message:		.asciz "Error: N is not even.\n"
result:				.asciz "Result: "
array:				.byte 0, 0, 0, 0, 0, 0

.text

		ldr r0, =N
		ldr r1, [r0]
		ands r2, r1, #1
		beq make_array
		b error
		
	make_array:
		mov r3, #0
		ldr r4, =array
		
	loop:
		swi 0x6d
		and r5, r0, #0xFF
		strb r5, [r4, r3]
		
		add r3, r3, #1
		cmp r3, r1
		blt loop
		
	sums:
		mov r3, #0
		mov r6, r1
		mov r7, #2
		swi 0x6A
		ldr r4, =array
		
	loop2:
		ldrb r7, [r4, r3]
		sub r8, r1, r3
		sub r8, r8, #1
		ldrb r9, [r4, r8]
		add r10, r7, r9
		mov r0, #1
		ldr r1, =result
		swi 0x69
		mov r0, #1
		mov r1, r10
		swi 0x6b
	
	exit:
		mov r0, #0
		swi 0x11
		
	error:
		mov r0, #1
		ldr r1, =error_message
		swi 0x69
		swi 0x11

.end