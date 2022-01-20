import { Box, Button } from '@mui/material';
import React from 'react';
import UploadIcon from '@mui/icons-material/Upload';
//dropzone
import Dropzone from 'react-dropzone';

export default function MembershipUpload({ setFiles}) {
    const handleSelectedFile = (acceptedFiles) => {
        if (acceptedFiles.length) {
            setFiles(acceptedFiles);
        }
    };
    
    return(<Dropzone onDrop={acceptedFiles => handleSelectedFile(acceptedFiles)} accept=".xls,.xlsx,.csv" multiple={false}>
        {({ getRootProps, getInputProps }) => {
            return(
                <Box mt={2} {...getRootProps()}>
                    <input {...getInputProps()} />
                    <Button
                        style={{cursor:'pointer',width: '165px',}}
                        variant="outlined"
                        startIcon={<UploadIcon />}
                    >
                        Upload Excel
                    </Button>
                </Box>
            )}}
    </Dropzone>)
}