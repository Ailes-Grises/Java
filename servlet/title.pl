#!/usr/bin/perl

use strict;
use warnings;

my $filenamer = "README.md";
my $filenamew = "hoge.cp";

open(my $fdr, $filenamer) or die "($!)";
open(my $fdw, '>'.$filenamew) or die "($!)";

while(<$fdr>){
	chomp($_);
	if(/#/){
		print $fdw "$_\n";
	}
}

close($fdr);
close($fdw);
