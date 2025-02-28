.data
question:			.asciz "Guess a number between 0 and 255: "
high_message:		.asciz "High. "
low_message:		.asciz "Low. "
correct_message:	.asciz "Correct.\n"
amount_of_guesses:	.asciz " guesses.\n"

.text
		
		swi 0x6d
		and r4, r0, #0xFF
		mov r5, #0
		
	game:
		mov r0, #1
		ldr r1,=question
		swi 0x69
		mov r0, #0
		swi 0x6c
		mov r1, r0
		add r5, r5, #1
		cmp r1, r4
		beq correct
		bgt high
		b low
		
	high:
		mov r0, #1
		ldr r1,=high_message
		swi 0x69
		b game
		
	low:
		mov r0, #1
		ldr r1,=low_message
		swi 0x69
		b game
		
	correct:
		mov r0, #1
		ldr r1,=correct_message
		swi 0x69
		mov r0, #1
		mov r1, r5
		swi 0x6b
		mov r0, #1
		ldr r1,=amount_of_guesses
		swi 0x69
		mov r0, #0
		swi 0x11

.end