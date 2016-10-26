#!/bin/sh

find / -type f -size +50000k -exec ls -lh {} \; | awk '{ print $8 ": " $5 }'

# or

du -a /var | sort -n -r | head -n 10


# OR

ls -lS | head +10



#!/bin/sh

DISC=$1
PARTITION=`df -h|grep $DISC|awk �{print $1}�`
SIZE =`df -h| grep $DISC|awk �{print $2}�`
USED =`df -h|grep $DISC|awk �{print $3}�`
FREE =`df -h|grep $DISC|awk �{print $4}�`

echo �Partition: $PARTITION�
echo �Total size:$SIZE�
echo �Used space:$USED�
echo �Free space:$FREE�

# OR

fdisk -l

df -h

