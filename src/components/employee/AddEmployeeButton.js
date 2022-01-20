import React from 'react';
import AddOutlinedIcon from '@mui/icons-material/AddOutlined';
import { Button } from '@mui/material';
import { useHistory } from 'react-router-dom';
import { PATH_DASHBOARD } from '../../routes/path';

export default function AddEmployeeButton () {
    const history = useHistory();
    return(<Button
        variant="outlined"
        onClick={() => history.push({ pathname: PATH_DASHBOARD.employee.add })}
        startIcon={<AddOutlinedIcon />}
    >
        Add Employee
    </Button>)
}