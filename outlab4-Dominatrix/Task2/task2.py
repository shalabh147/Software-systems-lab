import numpy as np
import sys

#np.set_printoptions(threshold=sys.maxsize)
def reconstruct_from_noisy_patches(input_dict, shape):
    """
    input_dict: 
    key: 4-tuple: (topleft_row, topleft_col, bottomright_row, bottomright_col): location of the patch in the original image. topleft_row, topleft_col are inclusive but bottomright_row, 
    bottomright_col are exclusive. i.e. if M is the reconstructed matrix. M[topleft_row:bottomright_row, topleft_col:bottomright_col] will give the patch.

    value: 2d numpy array: the image patch.

    shape: shape of the original matrix.
    """
    # Initialization: Initialise M, black_count, mid_count, white_count, mid_total
    #print(shape)
    M=np.zeros(shape)
    black_count=np.zeros(shape)
    mid_count=np.zeros(shape)
    white_count=np.zeros(shape)
    mid_total=np.zeros(shape)
    for topleft_row, topleft_col, bottomright_row, bottomright_col in input_dict: # no loop except this!
        tlr, tlc, brr, brc = topleft_row, topleft_col, bottomright_row, bottomright_col
        patch = input_dict[(tlr, tlc, brr, brc)]
        
        # change black_count, mid_count, white_count, mid_total here
        #b= patch==0
        black_count[tlr:brr,tlc:brc]+= (patch==0).astype(int)
        white_count[tlr:brr,tlc:brc]+= (patch==255).astype(int)
        mid_count[tlr:brr,tlc:brc]+= np.logical_and(patch>0,patch<255).astype(int)
        mid_total[tlr:brr,tlc:brc]+= patch*np.logical_and(patch>0,patch<255).astype(int)
        #print(patch)
    # Finally change M here
    #print(white_count)
    #print(black_count)  
    #print(mid_count)
    
    #print(b.shape)
    #print(M.shape)
    M[np.logical_and(mid_count==0,black_count>white_count)]=0
    
    M[np.logical_and(mid_count==0,white_count>=black_count)]=255
    
    #print(c)
    #div=np.divide(mid_total,mid_count) * c.astype(int)
    div=np.zeros(shape)
    #print(div)
    div[np.logical_not(mid_count==0)]=mid_total[np.logical_not(mid_count==0)]/mid_count[np.logical_not(mid_count==0)]
    #print(div)
    M[np.logical_not(mid_count==0)]=div[np.logical_not(mid_count==0)]
    
    M[np.logical_and(np.logical_and(mid_count==0,white_count==0),black_count==0)]=0;
    M=np.around(M,decimals=0)
    #print(M)
    #print(mid_total.astype(int))
    #a=np.array([0.5,1.4,2.1])
    #print(int(0==0))
    #print(M)
   # for i in np.nditer(M):
    #    if i==0:
     #       print(i)
    #print(M*(M==256).astype(int))
    #print(M)
    return M # You have to return the reconstructed matrix (M).

