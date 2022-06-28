package com.groupone.membershipgym.rest;

import com.groupone.membershipgym.entity.Attachment;
import com.groupone.membershipgym.response.AttachmentResponse;
import com.groupone.membershipgym.service.AttachmentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@AllArgsConstructor
public class AttachmentRest {

    private final AttachmentServiceImpl attachmentService;

    @PostMapping("/upload")
    public AttachmentResponse uploadFile(@RequestParam("file")MultipartFile file) throws Exception {

        Attachment attachment = null;
        String downloadUrl = "";
        attachment=this.attachmentService.saveAttachment(file);
        downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(attachment.getId()).toUriString();

        return new AttachmentResponse(attachment.getFileName(), downloadUrl, file.getContentType(), file.getSize());

    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {

        Attachment attachment = null;
        attachment = this.attachmentService.getAttachment(fileId);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName() + "\"").body(new ByteArrayResource(attachment.getData()));

    }

    @GetMapping("/showFile/{fileId}")
    public ResponseEntity<byte[]> showFile(@PathVariable String fileId) throws Exception{
        Attachment attachment = this.attachmentService.getAttachment(fileId);
        byte[] fileBytes = attachment.getData();

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(attachment.getFileType())).body(fileBytes);

    }

}