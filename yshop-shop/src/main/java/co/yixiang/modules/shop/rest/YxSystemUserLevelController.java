package co.yixiang.modules.shop.rest;

import cn.hutool.core.util.StrUtil;
import co.yixiang.aop.log.Log;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.shop.domain.YxSystemUserLevel;
import co.yixiang.modules.shop.service.YxSystemUserLevelService;
import co.yixiang.modules.shop.service.dto.YxSystemUserLevelQueryCriteria;
import co.yixiang.utils.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

/**
* @author hupeng
* @date 2019-12-04
*/
@Api(tags = "YxSystemUserLevel管理")
@RestController
@RequestMapping("api")
public class YxSystemUserLevelController {

    @Autowired
    private YxSystemUserLevelService yxSystemUserLevelService;

    @Log("查询")
    @ApiOperation(value = "查询")
    @GetMapping(value = "/yxSystemUserLevel")
    @PreAuthorize("hasAnyRole('ADMIN','YXSYSTEMUSERLEVEL_ALL','YXSYSTEMUSERLEVEL_SELECT')")
    public ResponseEntity getYxSystemUserLevels(YxSystemUserLevelQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(yxSystemUserLevelService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增")
    @ApiOperation(value = "新增")
    @PostMapping(value = "/yxSystemUserLevel")
    @PreAuthorize("hasAnyRole('ADMIN','YXSYSTEMUSERLEVEL_ALL','YXSYSTEMUSERLEVEL_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxSystemUserLevel resources){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        resources.setAddTime(OrderUtil.getSecondTimestampTwo());
        return new ResponseEntity(yxSystemUserLevelService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改YxSystemUserLevel")
    @ApiOperation(value = "修改YxSystemUserLevel")
    @PutMapping(value = "/yxSystemUserLevel")
    @PreAuthorize("hasAnyRole('ADMIN','YXSYSTEMUSERLEVEL_ALL','YXSYSTEMUSERLEVEL_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxSystemUserLevel resources){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        yxSystemUserLevelService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除YxSystemUserLevel")
    @ApiOperation(value = "删除YxSystemUserLevel")
    @DeleteMapping(value = "/yxSystemUserLevel/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','YXSYSTEMUSERLEVEL_ALL','YXSYSTEMUSERLEVEL_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        yxSystemUserLevelService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}