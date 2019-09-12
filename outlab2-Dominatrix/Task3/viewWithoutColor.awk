BEGIN{
	FS=",";
	RS="\r\n";
}
NR==1{
	for(i=1;i<=NF;i++){
	if($i=="Name")
	{
		rem=i
	}
	}

	for(i=1;i<=NF;i++)
	{
		if(i!=rem)
			for(j=1;j<=20;j++)
				printf("-")
	}
	printf("\n")

	for(i=1;i<=NF;i++)
	{
		if(i!=rem)
			printf("%20s",$i)
	}
	printf("\n")

	for(i=1;i<=NF;i++)
	{
	if(i!=rem)
	{
		for(j=1;j<=20;j++)
		{
			printf("-")
		}
	}
	}
	printf("\n");


}
NR>1{
	
	for(i=1;i<=NF;i++)
	{
		if(i!=rem)
		{
			printf("%20s",$i)
		}
	}
	printf("\n")
}
END{
}
