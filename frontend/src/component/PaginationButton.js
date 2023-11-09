// import * as React from 'react';
import ReactPaginate from "react-paginate";
import React from "react";
// import Stack from '@mui/material/Stack';

 export default function PaginationButtons(props) {
     const pagesTotal= props.pageInfo.totalPages;
     const reloadFunctionCurrent = props.reloadFunction;
     const handlePageClick=(e)=>{
         localStorage.setItem("currentPage",e.selected);
         console.log("1");
         reloadFunctionCurrent();
     }

  return (
      // <ReactPaginate
      //     // initialPage={1}
      //     nextLabel="next >"
      //     onPageChange={handlePageClick}
      //     pageRangeDisplayed={3}
      //     marginPagesDisplayed={2}
      //     pageCount={pagesTotal}
      //     previousLabel="< previous"
      //     pageClassName="page-item"
      //     pageLinkClassName="page-link"
      //     previousClassName="page-item"
      //     previousLinkClassName="page-link"
      //     nextClassName="page-item"
      //     nextLinkClassName="page-link"
      //     breakLabel="..."
      //     breakClassName="page-item"
      //     breakLinkClassName="page-link"
      //     containerClassName="pagination"
      //     activeClassName="active"
      //     renderOnZeroPageCount={null}
      //     page={localStorage.getItem("currentPage")}
      //
      // />

      <ReactPaginate
          nextLabel="next >"
          onPageChange={handlePageClick}
          pageRangeDisplayed={3}
          marginPagesDisplayed={2}
          pageCount={pagesTotal}
          previousLabel="< previous"
          pageClassName="page-item"
          pageLinkClassName="page-link"
          previousClassName="page-item"
          previousLinkClassName="page-link"
          nextClassName="page-item"
          nextLinkClassName="page-link"
          breakLabel="..."
          breakClassName="page-item"
          breakLinkClassName="page-link"
          containerClassName="pagination"
          activeClassName="active"
          renderOnZeroPageCount={null}
      />

      // <ReactPaginate
      //     previousLavel={"Prev"}
      //     nextLavel={"Next"}
      //     pageRangeDisplayed={3}
      //     marginPagesDisplayed={2}
      //     pageCount={pageInfo.totalPages}
      //     renderOnZeroPageCount={null}
      //     breakLabel="..."
      //
      //
      // />
    //   <div>
    // <button onClick={() => this.goToPage(0)}>First</button>
    // <button>Prev</button>
    // <button>Self</button>
    // <button>Next</button>
    // <button onClick={() => this.goToPage(this.state.pageInfo.totalPages - 1)}>Last</button>
    //   </div>
    // <Stack spacing={2}>
    //   <Pagination count={10} showFirstButton showLastButton />
    //   <Pagination count={10} hidePrevButton hideNextButton />
    // </Stack>
  );
}