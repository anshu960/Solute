import {Button, Avatar, Box, Checkbox, ListItem, ListItemAvatar, ListItemText, Typography } from "@mui/material";

import React, { useState } from "react";
import PersonAddIcon from '@mui/icons-material/PersonAdd';
import PersonRemoveIcon from '@mui/icons-material/PersonRemove';
const UserList = ({user, selectedUser, setSelectedUser}) => {
    const handleSelected = (_id) => {
        const selectedUserList = selectedUser.length ? [...selectedUser]: [];
        const index = selectedUserList.indexOf(_id);
        if(index > -1){
            selectedUserList.splice(index,1);
        }
        else{
            selectedUserList.push(_id)
        }
        setSelectedUser(selectedUserList)
    } 

    console.log(user, selectedUser)
    return(
        <ListItem
      // button
      to="#"
      disableGutters
      key={1}
      //component={RouterLink}
      sx={{
        py: 1.5,
        px: 2.5,
        '&:not(:last-of-type)': { mb: '1px' },
        ...(false && {
          backgroundColor: 'action.selected'
        })
      }}
    >
      <ListItemAvatar>
        <Avatar sx={{ backgroundColor: 'background.neutral' }}>
        </Avatar>
      </ListItemAvatar>
      <ListItemText
        primary={user.Name}
        secondary={
          <Typography
            variant="caption"
            sx={{
              mt: 0.5,
              display: 'flex',
              alignItems: 'center',
              color: 'text.disabled'
            }}
          >
             <Box
              component="div"
              sx={{ mr: 0.5, width: 16, height: 16 }}>
                  {user.MobileNumber}
            </Box>

            <Button sx={{marginLeft:10}} 
             onPress={()=>{handleSelected(user._id)}}>
            {selectedUser.indexOf(user._id) > -1 ?
            <PersonRemoveIcon color="red">

            </PersonRemoveIcon>
             : 
             <PersonAddIcon color="blue">

            </PersonAddIcon>
             }
            </Button>
            
            {/* <Checkbox
                sx={{ marginLeft: 10 }}
                Name="IsPresent"
                color="primary"
                checked={selectedUser.indexOf(user._id) > -1 || false}
                onChange = {(event)=>handleSelected(event, user._id)}
            /> */}
            {/* <Box
              component="img"
              //icon={NotificationsNoneIcon}
              sx={{ mr: 0.5, width: 16, height: 16 }}
            /> */}
           
            {/* @ts-ignore */}
            {/* {formatDistanceToNow(new Date(notification.createdAt))} */}
          </Typography>
        }
      />
    </ListItem>
    )
}

export default UserList;