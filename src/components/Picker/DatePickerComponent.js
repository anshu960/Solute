import React from 'react';
import DatePicker from 'react-datepicker';
import { Box } from '@mui/material';
import { styled } from '@mui/material/styles';

const BoxStyle = styled(Box)(({ theme }) => ({
    color:'#fff',
    marginRight: '7px',
    position: 'relative',
    [theme.breakpoints.between('1024', '1400')]: {
        width: '18%',
    },
    '& input': {
        padding: '10px 0px 10px 10px',
        border: '1px solid #cccccc',
        borderRadius: '4px',
    }
}));

 const DatePickerComponent = ({selectedDate, setDate}) => {
    return(<BoxStyle component="span">
        <DatePicker
            placeholderText="Select Date"
            selected={selectedDate}
            onChange={(date) => setDate(date)}
        />
    </BoxStyle>)
}

const memoDatePickerComponent = (prevState, nextState) => prevState.selectedDate === nextState.selectedDate;

export default React.memo(DatePickerComponent, memoDatePickerComponent)