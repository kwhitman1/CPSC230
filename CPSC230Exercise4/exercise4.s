.data

.text

		mov r1,#30
		mov r2,#20
		mov r3,#10
		
		cmp r1,r2
		ble check_next
		
		cmp r2,r3
		ble check_next
		
		b true
	
	check_next:
		cmp r2,r3
		bge false
		
		cmp r2,r1
		bge false
		
		b true
		
	true:
		swi 0x11
	
	false:
		swi 0x11
		
.end