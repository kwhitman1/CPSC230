.data
input_prompt: 	.asciz "Enter a number: "
search_prompt: 	.asciz "Enter a number to search: "
found_message: 		.asciz "Number found at index: %d\n"
not_found_message: 	.asciz "Number not found.\n"
newline: 		.asciz "\n"
N: 				.word 5  
array: 			.skip 20

.text

		mov sp, #0x10000
		ldr r0, =input_prompt
		bl get_number
		ldr r1, =N
		str r0, [r1]
		ldr r1, =N
		ldr r1, [r1]  
		mov r2, #0 

	loop:
		cmp r2, r1
		beq sort_array     
		ldr r0, =input_prompt
		bl get_number     
		ldr r3, =array
		add r3, r3, r2, lsl #2  
		str r0, [r3]        
		add r2, r2, #1     
		b loop

	sort_array:
		ldr r1, =N
		ldr r1, [r1]      
		sub r1, r1, #1    
		mov r2, #0    
		
	loop2:
		cmp r2, r1
		bge binary_search  
		mov r3, #0
		
	loop3:
		cmp r3, r1
		bge increment_loop2
		ldr r4, =array
		add r4, r4, r3, lsl #2
		ldr r5, [r4]      
		add r4, r4, #4
		ldr r6, [r4]    
		cmp r5, r6
		ble increment_loop3
		str r6, [r4, #-4]  
		str r5, [r4]

	increment_loop2:
		add r2, r2, #1
		b loop2

	increment_loop3:
		add r3, r3, #1
		b loop3

	binary_search:
		ldr r0, =search_prompt
		bl get_number    
		ldr r1, =array
		ldr r2, =N
		ldr r2, [r2]
		mov r3, #0        
		sub r4, r2, #1   
		bl binary_search_recursive
		b quit_program

	binary_search_recursive:
		cmp r3, r4
		bgt not_found      
		add r5, r3, r4
		mov r5, r5, LSR#1    
		ldr r6, [r1, r5, lsl #2]  
		cmp r6, r0
		beq found
		blt search_left
		b search_right

	search_left:
		sub r4, r5, #1  
		b binary_search_recursive

	search_right:
		add r3, r5, #1  
		b binary_search_recursive

	found:
		ldr r0, =found_message
		bl print_result
		b quit_program

	not_found:
		ldr r0, =not_found_message
		bl print_result

	quit_program:
		mov r7, #1       
		swi 0x11

	get_number:
		mov r2, r0        
		ldr r0, =input_prompt
		bl print_result         
		swi 0x11           
		mov r0, r0        
		bx lr             

	print_result:
		mov r7, #4         
		swi 0x11            
		bx lr             
.end